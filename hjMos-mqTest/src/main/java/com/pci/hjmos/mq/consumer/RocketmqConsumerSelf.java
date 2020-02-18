//package com.pci.hjmos.mq.consumer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//@RocketMQMessageListener(topic = "my-topic",consumerGroup = "my-group")
//public class RocketmqConsumerSelf implements RocketMQListener<String> {
//
//    @Override
//    public void onMessage(String s) {
//        System.out.println("通过rocketmq自身的注解接收到消息："+ s);
//    }
//}
