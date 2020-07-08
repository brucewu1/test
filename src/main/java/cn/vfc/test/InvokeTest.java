package cn.vfc.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InvokeTest {
    public static void setMethod(Class clazz,Student student){
        Field[] fields = clazz.getDeclaredFields();
        String[] values = {"a","b","c"};
        for (int i = 0;i<fields.length;i++){
            String name = fields[i].getName();
            fields[i].setAccessible(true);
            System.out.println(name);
            String methodName = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
            try {
                Method method = clazz.getDeclaredMethod(methodName, String.class);
                method.invoke(student, values[i]);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Student student = new Student();
        InvokeTest.setMethod(Student.class,student);
        System.out.println(student.toString());
    }
}
