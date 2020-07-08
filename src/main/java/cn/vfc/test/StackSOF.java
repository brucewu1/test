package cn.vfc.test;

public class StackSOF {
    private int num = 1;
    public void doSomething(){
        try {
            Thread.sleep(100000000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StackSOF stackSOF = new StackSOF();
        try {
            while (true){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        stackSOF.doSomething();
                    }
                }).start();
            }
        }catch (Throwable e){
            System.out.println("当前活跃线程数：" + stackSOF.num);
        }
    }
}
