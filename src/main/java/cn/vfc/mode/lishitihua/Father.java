package cn.vfc.mode.lishitihua;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Father {
    public Collection doSomething(HashMap map){
        System.out.println("父类被调用...");
        return map.values();
    }
}
