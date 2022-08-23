package com.yixin.jedisdemo;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class PhoneCode {
    public static void main(String[] args) {
        //verifyCode("12345678679");
        getRedisCode("12345678679","077138");


    }

    // 1. 生成6位的数字验证码
    public static String getCode(){
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int i1 = random.nextInt(10);
            code += i1;
        }
        return code;
    }

    // 2.每个手机每天只能发送三次  验证码放到redis中，设置过期时间
    public static void verifyCode(String phone){
        // 连接redis
        Jedis jedis = new Jedis("120.46.188.187", 6379);
        // 拼接key
        // 手机发送次数
        String countKey = "VerifyCode" + phone + ":count";
        // 验证码
        String codeKey = "VerifyCode" + phone + ":code";

        // 每天每个手机只能发送三次
        String count = jedis.get(countKey);
        if (count == null){
            // 没有发送次数 ，第一次发送
            // 设置发送次数是1
            jedis.setex(countKey,24*60*60,"1");
        }else if (Integer.parseInt(count) <= 2){
            // 发送次数+1
            jedis.incr(countKey);
        }else if (Integer.parseInt(count) > 2){
            System.out.println("该号码今天发送次数已超过3次");
            jedis.close();
            return;
        }

        String vcode = getCode();
        jedis.setex(codeKey,120,vcode);
        jedis.close();

    }

    // 3. 对比用户输入的验证码
    public static void getRedisCode(String phone,String code){
        Jedis jedis = new Jedis("120.46.188.187", 6379);
        String codeKey = "VerifyCode" + phone + ":code";
        String rcode = jedis.get(codeKey);
        if (code.equals(rcode)){
            System.out.println("成功");
        }else {
            System.out.println("验证码输入错误");
            System.out.println("输入："+code);
            System.out.println("redis获取："+ rcode);
        }
    }
}
