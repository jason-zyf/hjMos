//package com.pci.notify;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.List;
//
///**
// * @author zyting
// * @sinne 2020-04-06
// */
//public class NotifyTest {
//
//    public static void main(String[] args) {
//        JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
//
//        Jedis jedis = pool.getResource();
//        config(jedis);//建议在redis配置文件中设置
//
//        jedis.psubscribe(new KeyExpiredListener(), "__keyevent@0__:expired");//过期队列
//    }
//
//    private static void config(Jedis jedis) {
//        String parameter = "notify-keyspace-events";
//        List<String> notify = jedis.configGet(parameter);
//        if(notify.get(1).equals("")){
//            jedis.configSet(parameter, "Ex"); //过期事件
//        }
//    }
//}
//
