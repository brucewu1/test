package cn.vfc.zk.distributedlock;

import cn.vfc.zk.ClientFactory;
import cn.vfc.zk.cocurrent.FutureTaskScheduler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKLockTest {
    private static final Logger log = LoggerFactory.getLogger(ZKLockTest.class);
    int count = 0;
    private static final String ZK_ADDRESS = "127.0.0.1:2181";
    @Test
    public void testLock() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            FutureTaskScheduler.add(() -> {
                ZKLock lock = new ZKLock();
                lock.lock();

                for (int j = 0; j < 10; j++) {

                    count++;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("count = " + count);
                lock.unlock();

            });
        }

        Thread.sleep(Integer.MAX_VALUE);
    }


    @Test
    public void testzkMutex() throws InterruptedException {

        CuratorFramework client = ClientFactory.createSimple(ZK_ADDRESS);
        client.start();
        final InterProcessMutex zkMutex =
                new InterProcessMutex(client, "/mutex");
        ;
        for (int i = 0; i < 10; i++) {
            FutureTaskScheduler.add(() -> {

                try {
                    zkMutex.acquire();

                    for (int j = 0; j < 10; j++) {

                        count++;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("count = " + count);
                    zkMutex.release();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }

        Thread.sleep(Integer.MAX_VALUE);
    }
}
