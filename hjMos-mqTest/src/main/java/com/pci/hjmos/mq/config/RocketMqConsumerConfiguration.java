package com.pci.hjmos.mq.config;

import com.pci.hjmos.mq.util.bean.MessageEvent;
import com.pci.hjmos.mq.util.properties.RocketMQProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(RocketMQProperties.class)
@Slf4j
public class RocketMqConsumerConfiguration {

    /**
     * rocketmq 配置信息类
     */
    @Autowired
    private RocketMQProperties consumerMQProperties;

    /**
     * 事件监听
     */
    @Autowired
    private ApplicationEventPublisher publisher = null;


    private static boolean isFirstSub = true;

    /**
     * 系统当前时间
     */
    private static long startTime = System.currentTimeMillis();

    /**
     * 初始化消费者实例
     */
    @Bean
    public void initConsumerInstance(){
        if(consumerMQProperties == null){
            return ;
        }

        List<Map<String,Object>> consumerList = consumerMQProperties.getConsumerList();
        if(consumerList !=null && consumerList.size() > 0){
            consumerList.stream().forEach(map ->{
                try {
                    pushConsumer(map);
                } catch (MQClientException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 创建消费者实例
     * @param consumerConfig
     * @return
     */
    private DefaultMQPushConsumer pushConsumer(Map<String, Object> consumerConfig) throws MQClientException{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerConfig.get("groupName").toString());
        consumer.setNamesrvAddr(consumerConfig.get("namesrvAddr").toString());

        //判断是否是广播模式（默认集群模式）
        if (Boolean.parseBoolean(consumerConfig.get("consumerBroadCasting").toString())) {
            consumer.setMessageModel(MessageModel.BROADCASTING);
        }

        //设置批量消费
        consumer.setConsumeMessageBatchMaxSize(Integer.parseInt(consumerConfig.get("consumerBatchMaxSize").toString()) == 0 ? 1 : Integer.parseInt(consumerConfig.get("consumerBatchMaxSize").toString()));

        //获取topic和tag
        consumer.subscribe(consumerConfig.get("topic").toString(), consumerConfig.get("tags").toString());

        if (Boolean.parseBoolean(consumerConfig.get("enableOrderConsumer").toString())) {
            // 顺序消费
            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeOrderlyContext context) {
                    try {
                        context.setAutoCommit(true);
                        msgs = filterMessage(msgs);
                        if (msgs.size() == 0){
                            return ConsumeOrderlyStatus.SUCCESS;
                        }
                        publisher.publishEvent(new MessageEvent(msgs, consumer));
                    }catch (Exception e){
                        e.printStackTrace();
                        return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });

        }else {
            // 并发消息
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    try {
                        //过滤消息
                        msgs = filterMessage(msgs);
                        if (msgs.size() == 0)
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        publisher.publishEvent(new MessageEvent(msgs, consumer));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    try {
                        consumer.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    log.info("rocketmq consumer server is starting....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        return consumer;
    }

    /**
     * 消息过滤
     * @param msgs
     *  enableHistoryConsumer  启动时是否消费历史消息
     * @return
     */
    private List<MessageExt> filterMessage(List<MessageExt> msgs) {
        if (isFirstSub && !consumerMQProperties.isEnableHistoryConsumer()) {
            msgs = msgs.stream()
                    .filter(item -> startTime-item.getBornTimestamp() < 0)
                    .collect(Collectors.toList());
        }
        if (isFirstSub && msgs.size() > 0) {
            isFirstSub = false;
        }
        return msgs;
    }


}
