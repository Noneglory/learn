package com.mico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@RestController
@RequestMapping(value = "redis",headers = "Accept=application/json",produces = "application/json;charset=UTF-8")
public class BookController {
    /**
     * 此处的RedisTemplate。StringRedisTemplate是在application.properties里配置后，springboot自动配置的。
     */
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/redis1")
    public String test1(){
        //使用StringredisTemplate
        ValueOperations<String,String> ops1 =stringRedisTemplate.opsForValue();
        ops1.set("name","三国演义");
        String name = ops1.get("name");
        return name;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/setvalue")
    public boolean setKeyValue(
            @RequestHeader(value="uid",required = false) long uid,
            @RequestParam(value="key",required=true) String key,
            @RequestParam(value="value",required=true) String value){

        ValueOperations<String,String> ops2 = stringRedisTemplate.opsForValue();
        if(key==null || "".equals(key))
        {
            return  false;
        }
        ops2.set(key,value);
        return true;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getvalue")
    public String getValue(
            @RequestHeader(value = "uid",required = false) long uid,
            @RequestParam(value = "key",required = true) String key)
    {
        if(key==null || " ".equals(key))
        {
            return null;
        }
        ValueOperations<String, String> ops3 = stringRedisTemplate.opsForValue();
        return ops3.get(key);
    }


}
