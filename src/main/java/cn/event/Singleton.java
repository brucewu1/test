package cn.event;

public class Singleton {
    /*private static Singleton instance = new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return instance;
    }*/
    /*private static Singleton instance;
    private Singleton(){}
    public synchronized static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }*/

    /*private static class SingletonHold{
        private static final Singleton instance = new Singleton();
    }
    private Singleton(){}
    public static final Singleton getInstance(){
        return SingletonHold.instance;
    }*/

    private static volatile Singleton instance;
    private Singleton(){}
    public static Singleton getInstance(){
        if(null == instance){
            synchronized (Singleton.class){
                if(null == instance){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
