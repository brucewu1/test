package cn.vfc.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ClientFactory {
    public static CuratorFramework createSimple(String connectionString){
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(connectionString,retryPolicy);
    }

    public static CuratorFramework createOptions(String connectionString, RetryPolicy retryPolicy,int connectionTimeOut,int sessionTimeOut){
        return CuratorFrameworkFactory.builder()
                .connectString(connectionString)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeOut)
                .sessionTimeoutMs(sessionTimeOut)
                .build();
    }
}
