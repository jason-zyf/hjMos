package com.pci.hjMos.common;

import com.pci.hjMos.common.api.redis.RedisService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

    @Resource
    RedisService redisService;

    @GetMapping("/hha")
    public String test(){
        redisService.set("a", "jdka");
        return redisService.get("a").toString();
    }

}
