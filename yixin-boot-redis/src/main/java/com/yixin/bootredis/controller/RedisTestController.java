package com.yixin.bootredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/testRedis")
    public String testRedis(){
        redisTemplate.opsForValue().set("name","lucy");
        String name = (String)redisTemplate.opsForValue().get("name");
        return name;
    }
}
