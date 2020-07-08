package cn.vfc.zk.distributedlock;

/**
 * create by 尼恩 @ 疯狂创客圈
 **/
public interface Lock {

    boolean lock() throws Exception;

    boolean unlock();
}
