//package com.pci.redisspring;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
///**
// * @author zyting
// * @sinne 2020-04-06
// */
//@Component
//public class RedisSunscribeThread implements CommandLineRunner {
//
////    @Autowired
////    private RedisTemplate<String, String> redisTemplate;
//    @Autowired(required = false)
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Override
//    public void run(String... args) throws Exception {
//        redisTemplate.execute(new RedisCallback() {
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                connection.pSubscribe(new MessageListener() {
//                    @Override
//                    public void onMessage(Message message, byte[] pattern) {
//                        System.out.println("message:"+message);
//                        //业务处理
//
//                    }
//                },"*@0*".getBytes());
//                return null;
//            }
//        });
//    }
//
//}
