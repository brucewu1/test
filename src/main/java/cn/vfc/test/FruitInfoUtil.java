package cn.vfc.test;

import java.lang.reflect.Field;

public class FruitInfoUtil {
    public static void getFruitInfo(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                String strFruitProvicer = "编号" + fruitProvider.id() + "名字" + fruitProvider.name() + "地址" + fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
        }
    }
}
