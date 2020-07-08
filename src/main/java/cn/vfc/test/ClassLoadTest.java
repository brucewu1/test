package cn.vfc.test;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoadTest {
    public static void main(String[] args) throws Exception{
        /*ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (IOException e){
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object o = classLoader.loadClass("cn.vfc.test.ClassLoadTest").newInstance();
        System.out.println(o.getClass());
        System.out.println(o instanceof ClassLoadTest);*/
        ClassLoadTest classLoadTest = new ClassLoadTest();
        System.out.println(classLoadTest.getClass());
        System.out.println(classLoadTest instanceof ClassLoadTest);
    }
}
