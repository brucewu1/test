package cn.vfc.gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        /*Student student = new Student();
        student.setAge(17);
        student.setName("tanting");
        student.setSex('女');
        JSONObject jsonObject = new JSONObject();
        Gson gson = new Gson();
        String s = gson.toJson(student);
        System.out.println(s);
        jsonObject.put("txn",student);
        jsonObject.put("sign","sdjfs");
        System.out.println(jsonObject.toJSONString());*/
        String s = "{\"name\":\"tanting\",\"age\":17,\"sex\":\"女\"}";
        Student map = new Gson().fromJson(s, Student.class);
        String txn = map.getName();
        System.out.println(map);
        String a = null;
        String s1 = JSON.toJSONString(a);
        System.out.println(s1);
    }
}
