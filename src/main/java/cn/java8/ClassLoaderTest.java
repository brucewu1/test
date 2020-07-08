package cn.java8;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception{
        /*ClassLoader classLoader = new ClassLoader(){
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String filename = name.substring(name.lastIndexOf(".")+1)+".class";
                InputStream is = getClass().getResourceAsStream(filename);
                if(is ==null){
                    return super.loadClass(name);
                }
                System.out.println("aaaaa");
                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };*/

        Object o = ClassLoaderTest.class.getClassLoader().loadClass("cn.java8.ClassLoaderTest").newInstance();
        System.out.println(o.getClass());
        System.out.println(o instanceof ClassLoaderTest);
    }
}
