package cn.vfc.zk.publishsubscribe;

import cn.vfc.zk.ClientFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKWatcherDemo {
    private static final Logger log = LoggerFactory.getLogger(ZKWatcherDemo.class);
    private static final String ZK_ADDRESS = "127.0.0.1:2181";
    private String workerPath = "/test/listener/remoteNode";
    private String subWorkerPath = "/test/listener/remoteNode/id-";

    @Test
    public void testWatcher() throws Exception{
        CuratorFramework client = ClientFactory.createSimple(ZK_ADDRESS);

        //创建
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(workerPath);

        try {

            Watcher w = new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("监听到的变化 watchedEvent = " + watchedEvent);
                }
            };

            byte[] content = client.getData()
                    .usingWatcher(w).forPath(workerPath);

            System.out.println("监听节点内容：" + new String(content));

            // 第一次变更节点数据
            client.setData().forPath(workerPath, "第1次更改内容".getBytes());

            // 第二次变更节点数据
            client.setData().forPath(workerPath, "第2次更改内容".getBytes());

            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNodeCache() throws Exception{

        //检查节点是否存在，没有则创建
        CuratorFramework client = ClientFactory.createSimple(ZK_ADDRESS);
        client.start();
        client.create()
              .creatingParentsIfNeeded()
              .withMode(CreateMode.PERSISTENT)
              .forPath(workerPath);

        try {
            NodeCache nodeCache =
                    new NodeCache(client, workerPath, false);
            NodeCacheListener l = new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    ChildData childData = nodeCache.getCurrentData();
                    log.info("ZNode节点状态改变, path={}", childData.getPath());
                    System.out.println("ZNode节点状态改变, path={}"+ childData.getPath());
                    log.info("ZNode节点状态改变, data={}", new String(childData.getData(), "Utf-8"));
                    System.out.println("ZNode节点状态改变, data={}"+new String(childData.getData(), "Utf-8"));
                    log.info("ZNode节点状态改变, stat={}", childData.getStat());
                    System.out.println("ZNode节点状态改变, stat={}"+ childData.getStat());
                }
            };
            nodeCache.getListenable().addListener(l);
            nodeCache.start();

            // 第1次变更节点数据
            client.setData().forPath(workerPath, "第1次更改内容".getBytes());
            Thread.sleep(1000);

            // 第2次变更节点数据
            client.setData().forPath(workerPath, "第2次更改内容".getBytes());

            Thread.sleep(1000);

            // 第3次变更节点数据
            client.setData().forPath(workerPath, "第3次更改内容".getBytes());
            Thread.sleep(1000);

            // 第4次变更节点数据
//            client.delete().forPath(workerPath);
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            log.error("创建NodeCache监听失败, path={}", workerPath);
        }
    }

    @Test
    public void testPathChildrenCache() throws Exception{
        CuratorFramework client = ClientFactory.createSimple(ZK_ADDRESS);
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(workerPath);
        try {
            PathChildrenCache cache = new PathChildrenCache(client, workerPath, true);
            PathChildrenCacheListener listener = new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    ChildData data = event.getData();
                    switch (event.getType()){
                        case CHILD_ADDED:
                            log.info("字节的增加，path={},data={}",data.getPath(),new String(data.getData(),"utf-8"));
                            break;
                        case CHILD_UPDATED:
                            log.info("字节的更新，path={},data={}",data.getPath(),new String(data.getData(),"utf-8"));
                            break;
                        case CHILD_REMOVED:
                            log.info("字节的删除，path={},data={}",data.getPath(),new String(data.getData(),"utf-8"));
                        default:
                            break;
                    }
                }
            };
            cache.getListenable().addListener(listener);
            cache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
            Thread.sleep(1000);
            for(int i=0;i<3;i++){
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(subWorkerPath + i);
            }
            Thread.sleep(3000);
            //删除子节点
            for(int i=0;i<3;i++){
                client.delete().forPath(subWorkerPath + i);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
