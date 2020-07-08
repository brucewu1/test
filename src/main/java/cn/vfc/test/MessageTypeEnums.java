package cn.vfc.test;

import java.util.HashMap;
import java.util.Map;

public enum MessageTypeEnums {
    CHECK_IN("1"),
    CHECK_OUT("2"),
    ;
    private String value;

    public static Map<String,MessageTypeEnums> map = new HashMap<>(2);
    public String getValue() {
        return value;
    }

    MessageTypeEnums(String value) {
        this.value = value;
    }
    public static MessageTypeEnums getEnum(String key){
        return map.get(key);
    }

    static {
        for (MessageTypeEnums m : MessageTypeEnums.values()){
            map.put(m.getValue(),m);
        }
    }

    public static void main(String[] args) {
        MessageTypeEnums anEnum = getEnum("1");
        switch (anEnum){
            case CHECK_IN:
                System.out.println(CHECK_IN.name());
                break;
            case CHECK_OUT:
                System.out.println("22");
                break;
            default:
                System.out.println("33");
                break;
        }
    }
}
