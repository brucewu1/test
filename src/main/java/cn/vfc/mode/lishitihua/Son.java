package cn.vfc.mode.lishitihua;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Son extends Father {
    public Collection doSomething(Map map){
        System.out.println("子类被调用....");
        return map.values();
    }
}
