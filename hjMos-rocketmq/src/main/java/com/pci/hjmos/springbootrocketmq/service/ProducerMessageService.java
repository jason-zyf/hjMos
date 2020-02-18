package com.pci.hjmos.springbootrocketmq.service;

import com.pci.hjmos.springbootrocketmq.entity.ProduceMessage;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.exception.RemotingException;

public interface ProducerMessageService {

    /**
     * 生产消息的唯一对外服务入口
     * @param produceMessage
     * @return
     */
    boolean produceMessage(ProduceMessage produceMessage);

    /**
     * 发送同步消息
     * @param produceMessage
     */
    SendResult sendSyncMsg(ProduceMessage produceMessage) throws Exception;

    void sendAsyncMsg(ProduceMessage produceMessage) throws Exception;

    void sendOneWayMsg(ProduceMessage produceMessage) throws Exception;

    SendResult sendTransactionMsg(ProduceMessage produceMessage) throws Exception;

    SendResult sendMsgOrder(ProduceMessage produceMessage,int orderId) throws Exception;

}
