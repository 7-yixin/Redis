package com.yixin.jedisdemo;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisDemo1 {
    public static void main(String[] args) {
        // 创建Jedis对象
        Jedis jedis = new Jedis("120.46.188.187", 6379);

        // 测试
        System.out.println(jedis.ping());
    }

    // 操作 zset
    @Test
    public void demo5() {
        Jedis jedis = new Jedis("120.46.188.187", 6379);

        jedis.zadd("china",100d,"shanghai");
        System.out.println(jedis.zrange("china", 0, -1));
    }

    // 操作 hash
    @Test
    public void demo4() {
        Jedis jedis = new Jedis("120.46.188.187", 6379);

        jedis.hset("users","age","20");
        String hget = jedis.hget("users", "age");
        System.out.println(hget);
    }

    @Test
    public void demo3() {
        Jedis jedis = new Jedis("120.46.188.187", 6379);
        jedis.sadd("name","lucy","mary");
        Set<String> names = jedis.smembers("name");
        for (String name : names){
            System.out.println(name);
        }


    }


        // 操作
    @Test
    public void demo2() {
        Jedis jedis = new Jedis("120.46.188.187", 6379);

        jedis.lpush("k5","lucy","mary","jack");
        List<String> k5 = jedis.lrange("k5", 0, -1);
        System.out.println(k5);

        jedis.flushDB();
    }

        // 操作Key  String
    @Test
    public void demo1(){
        Jedis jedis = new Jedis("120.46.188.187", 6379);

        // 添加
        jedis.set("k2","yixin");
        jedis.mset("k3","v3","k4","v4");
        Set<String> keys = jedis.keys("*");
        for (String key : keys){
            System.out.println(key);
        }

        System.out.println(jedis.get("k2"));
        System.out.println(jedis.get("k4"));
    }

}
