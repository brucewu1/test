package cn.vfc.nio.futuretask;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaFutureDemo {
    public static final int SLEEP_GAP = 1000;
    public static String getCurrentName(){
        return Thread.currentThread().getName();
    }

    static class HotWaterJob implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("清洗茶具");
                System.out.println("灌入凉水");
                System.out.println("放在火上");
                Thread.sleep(SLEEP_GAP);
                System.out.println("水开了");
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            System.out.println("运行结束");
            return true;
        }
    }

    static class WashJob implements Callable<Boolean>{
        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("洗茶壶");
                System.out.println("洗茶杯");
                System.out.println("拿茶叶");
                Thread.sleep(SLEEP_GAP);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("清洗异常");
                return false;
            }
            System.out.println("清洗工作完成");
            return true;
        }
    }

    //新建一个异步业务类型，作为泡茶喝主线程类
    static class MainJob implements Runnable{
        boolean waterOk = false;
        boolean cupOk = false;
        int gap = SLEEP_GAP / 10;
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(gap);
                    System.out.println("读书中。。。。");
                }catch (Exception e){
                    e.printStackTrace();
                }
                if( waterOk && cupOk){
                    drinkTre(waterOk,cupOk);
                }
            }
        }

        private void drinkTre(boolean waterOk, boolean cupOk) {
            if(waterOk && cupOk){
                System.out.println("泡茶喝，喝茶中");
                this.waterOk = false;
                this.gap = SLEEP_GAP * 3;
            }else if(! waterOk){
                System.out.println("烧水失败，没有茶喝了");
            }else if(! cupOk){
                System.out.println("杯子洗不了，没有茶喝了");
            }
        }
    }

    public static void main(String[] args) {
        MainJob mainJob = new MainJob();
        Thread mainThread = new Thread(mainJob, "主线程");
        mainThread.start();

        Callable<Boolean> hotWaterJob = new HotWaterJob();
        Callable<Boolean> washJob = new WashJob();
        ExecutorService jpool = Executors.newFixedThreadPool(10);
        //包装java线程池，创建guava线程池
        ListeningExecutorService gpool = MoreExecutors.listeningDecorator(jpool);
        ListenableFuture<Boolean> hotFuture = gpool.submit(hotWaterJob);
        Futures.addCallback(hotFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if(result){
                    mainJob.waterOk = true;
                }
            }
            @Override
            public void onFailure(Throwable t) {
                System.out.println("烧水失败，没有茶喝了");
            }
        });
        ListenableFuture<Boolean> washFuture = gpool.submit(washJob);
        Futures.addCallback(washFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if(result){
                    mainJob.cupOk = true;
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("杯子洗不了，没有茶喝了");
            }
        });
    }
}
