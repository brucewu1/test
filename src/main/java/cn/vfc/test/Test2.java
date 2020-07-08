package cn.vfc.test;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

public class Test2{
    public static void main(String[] args) throws Exception{
        Thread t = new Thread(()->{
            System.out.println("start...");
            LockSupport.park();
            System.out.println("continue..");
        });
        t.start();
        Thread.sleep(1000);
        LockSupport.unpark(t);

    }

}
