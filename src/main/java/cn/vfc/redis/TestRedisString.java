package cn.vfc.redis;

import cn.vfc.list.Student;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Set;

public class TestRedisString {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("154.8.220.47",7000);
        Student student = new Student();
        //jedis.set("ab",student);
        jedis.set("aa", "ad");
        //jedis.del("aa");
        HashMap<String, String> map = new HashMap<>();

        map.put("name","redis");
        jedis.hmset("bb",map);
        System.out.println(jedis.get("aa"));

    }
}
