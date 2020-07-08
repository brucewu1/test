package cn.vfc.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    interface IHello{
        void sayHello();
    }
    static class Hello implements IHello{
        @Override
        public void sayHello() {
            System.out.println("Hello world");
        }
    }

    static class DynamicProxy implements InvocationHandler{
        private Object originalObj;
        Object bind(Object originalObj){
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),originalObj.getClass().getInterfaces(),this);
        }
        @Override
        public java.lang.Object invoke(java.lang.Object proxy, Method method, java.lang.Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj,args);
        }
    }

    public static void main(String[] args) {
        IHello hello = (IHello)new DynamicProxy().bind(new Hello());
        //IHello hello = new Hello();
        hello.sayHello();
    }
}
