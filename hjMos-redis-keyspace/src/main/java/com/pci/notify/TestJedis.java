//package com.pci.notify;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * @author zyting
// * @sinne 2020-04-06
// */
//public class TestJedis {
//
//    public static void main(String[] args) {
//        JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
//        Jedis jedis = pool.getResource();
//        jedis.setex("notify-task-001", 5,"empty");
//    }
//
//}
