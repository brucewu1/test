package cn.vfc.nio.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class JavaFutureDemo {
    public static final int SLEEP_GAP = 1000;
    public static String getCurrentName(){
        return Thread.currentThread().getName();
    }

    static class HotWaterJob implements Callable<Boolean>{
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
    public static void drinkTea(boolean waterOk,boolean cupOk){
        if(waterOk && cupOk){
            System.out.println("泡茶喝");
        }else if(!waterOk){
            System.out.println("水没开，没茶喝了");
        }else if(!cupOk){
            System.out.println("杯子不ok，没茶喝了");
        }
    }

    public static void main(String[] args) {
        Callable<Boolean> hotWaterJob = new HotWaterJob();
        FutureTask<Boolean> hTask = new FutureTask<>(hotWaterJob);
        Thread t1 = new Thread(hTask,"烧水线程");
        Callable<Boolean> washJob = new WashJob();
        FutureTask<Boolean> wTask = new FutureTask<>(washJob);
        Thread t2 = new Thread(wTask,"清洗线程");
        t1.start();
        t2.start();
        Thread.currentThread().setName("主线程");
        try {
            Boolean hotBoolean = hTask.get();
            Boolean washBoolean = wTask.get();
            drinkTea(hotBoolean,washBoolean);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("运行结束");
    }
}
