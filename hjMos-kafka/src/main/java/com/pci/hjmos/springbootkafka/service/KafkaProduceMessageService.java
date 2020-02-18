package com.pci.hjmos.springbootkafka.service;

import com.pci.hjmos.springbootkafka.entity.KafkaProduceMessage;
import com.pci.hjmos.springbootkafka.entity.Result;

public interface KafkaProduceMessageService {

    /**
     * 生产消息的唯一对外服务入口
     * @param produceMessage
     * @return
     */
    boolean produceMessage(KafkaProduceMessage produceMessage);

    /**
     * 发送同步消息
     * @param topic 消息主题
     * @param content 消息内容
     * @return 消息发送结果
     */
    Result sendSyncMsg(String topic, String content) throws Exception;

    /**
     * 发送同步消息
     * @param topic 消息主题
     * @param content 消息内容
     * @param callback 回调方法对象
     */
    void sendAsyncMsg(String topic, String content, MQCallback callback) throws Exception;

}
