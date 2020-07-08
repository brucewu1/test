package cn.vfc.test;

import java.util.concurrent.CountDownLatch;

public class SingleInstance {
    private static SingleInstance instance = null;

    private SingleInstance(){
    }

    public static SingleInstance getInstance(){
        if(instance == null){
            synchronized(instance){

            }
        }
        return instance;
    }
    /*private static class SingleFactory{
        private static SingleInstance instance = new SingleInstance();
    }

    public static SingleInstance getInstance(){
        return SingleFactory.instance;
    }*/
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int num = 100;
        final CountDownLatch latch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        SingleInstance instance = SingleInstance.getInstance();
                        System.out.println(Thread.currentThread()+instance.toString());
                    }
                }
            }).start();
            latch.countDown();
        }
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
