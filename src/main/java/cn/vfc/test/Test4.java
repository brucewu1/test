package cn.vfc.test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Test4 {
    class GrandFather{
        void think(){
            System.out.println("i am grandfather");
        }
    }
    class Father extends GrandFather{
        void think(){
            System.out.println("i am father");
        }
    }
    class Son extends Father {
        @Override
        void think() {
        try {
            MethodType methodType = MethodType.methodType(void.class);
            MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "think", methodType, getClass());
            mh.invoke(this);
        }catch (Throwable e){
            e.printStackTrace();
        }
        }
    }

    public static void main(String[] args) {
        (new Test4().new Son()).think();
    }
}
