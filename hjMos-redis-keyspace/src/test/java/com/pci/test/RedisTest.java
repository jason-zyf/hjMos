package com.pci.test;

import com.pci.RedisApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author zyting
 * @sinne 2020-04-06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisApplication.class})
@Slf4j
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test(){
        System.out.println("111121");
    }

    @Test
    public void testRedis(){
        System.out.println("redisTemplate: " + redisTemplate);
        redisTemplate.opsForValue().set("b", "哈哈");
        Object age = redisTemplate.opsForValue().get("b");
        System.out.println("b: " + age);
    }

     @Test
    public void test1(){
        System.out.println("redisTemplate:"+redisTemplate);
        redisTemplate.opsForValue().set("keyTest","123",5, TimeUnit.SECONDS);
        Object keyTest1 = redisTemplate.opsForValue().get("keyTest");
        log.info("设置keyTest值：" + keyTest1);
    }



}
