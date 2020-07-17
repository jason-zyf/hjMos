package com.pci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zyting
 * @sinne 2020-04-06
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        final JedisConnectionFactory jedisConnectionFactory=new JedisConnectionFactory();

        jedisConnectionFactory.setHostName("192.168.236.137");
        jedisConnectionFactory.setPort(6379);
        template.setConnectionFactory(jedisConnectionFactory);
        template.afterPropertiesSet();
        // redis存取对象的关键配置
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        // ObjectRedisSerializer类为java对象的序列化和反序列化工具类
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        template.setValueSerializer(jdkSerializationRedisSerializer);
        // 为hashvalue添加序列化和反序列化类
        template.setHashValueSerializer(jdkSerializationRedisSerializer);
        return template;
    }

}
