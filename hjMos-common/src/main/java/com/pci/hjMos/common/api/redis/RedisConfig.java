package com.pci.hjMos.common.api.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis 配置文件
 *
 * @author lsk
 * @class_name RedisConfig
 * @date 2019-05-23
 */
@Configuration
public class RedisConfig {

    @Bean(name = "defaultSerializer")
    public DefaultSerializer defaultSerializer() {
        return new DefaultSerializer();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory, DefaultSerializer defaultSerializer) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();
        // redis存取对象的关键配置
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        // ObjectRedisSerializer类为java对象的序列化和反序列化工具类
        template.setValueSerializer(defaultSerializer);
        // 为hashvalue添加序列化和反序列化类
        template.setHashValueSerializer(defaultSerializer);
        return template;
    }
}
