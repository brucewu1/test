package cn.vfc.mode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BirdProxy implements InvocationHandler {
    private Flyable flyable;

    public BirdProxy(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        Object value = method.invoke(flyable, args);
        System.out.println("fly end .......");
        return value;
    }
}
