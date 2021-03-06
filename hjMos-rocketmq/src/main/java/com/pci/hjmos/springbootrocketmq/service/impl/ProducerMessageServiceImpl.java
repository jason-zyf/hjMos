package com.pci.hjmos.springbootrocketmq.service.impl;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.pci.hjmos.springbootrocketmq.entity.ProduceMessage;
import com.pci.hjmos.springbootrocketmq.exception.MqSendException;
import com.pci.hjmos.springbootrocketmq.service.ProducerMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@Service
public class ProducerMessageServiceImpl implements ProducerMessageService {

    /**
     * 普通生产者
     */
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    /**
     * 事务消息生产者
     */
    @Autowired
    private TransactionMQProducer transactionMQProducer;

    private RocketSendCallback rocketSendCallback = new RocketSendCallback();

    /**
     * 发送消息的方法入口
     * @param produceMessage
     * @return
     */
    @Override
    public boolean produceMessage(ProduceMessage produceMessage) {
        return produceMessageCore(produceMessage);
    }

    private Message createMessage(ProduceMessage produceMessage) {
        @NotBlank String topic = produceMessage.getTopic();
        @NotBlank String content = produceMessage.getContent();
        @NotBlank String tag = produceMessage.getTag();
        String keys = produceMessage.getKeys();
        return new Message(topic, tag, keys, content.getBytes());
    }

    private boolean produceMessageCore(ProduceMessage produceMessage) {
        @NotBlank String topic = produceMessage.getTopic();
        @NotBlank String content = produceMessage.getContent();
        @NotBlank String tag = produceMessage.getTag();
        String keys = produceMessage.getKeys();
        /*if (!verifyJson(content)) {
            throw new MqSendException("消息内容不是json格式");
        }*/
        try {
            sendMsg(topic, tag, keys, content);
        } catch (Exception e) {
            log.error("发送普通消息失败", e);
            throw new MqSendException(e);
        }
        return true;
    }

    /**
     * 校验json 字符串
     * @param json
     * @return
     */
    private boolean verifyJson(String json) {
        try {
            JSONObject.parseObject(json);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(json);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 发送普通消息
     */
    private SendResult sendMsg(String topic, String tags, String keys, String content) throws Exception {
        Message msg = new Message(topic, tags, keys, content.getBytes());
        SendResult result = defaultMQProducer.send(msg);
        this.logMsg(msg, result);
        return result;
    }

    /**
     * 支持顺序发送消息
     */
    private SendResult sendMsgOrder(String topic, String tags, String keys, String content, int orderId) throws Exception {
        Message msg = new Message(topic, tags, keys, content.getBytes());
        SendResult sendResult = defaultMQProducer.send(msg, (List<MessageQueue> mqs, Message message, Object arg) -> {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
                , orderId);
        this.logMsg(msg, sendResult);
        return sendResult;
    }

    /**
     * 发送同步消息
     * @param produceMessage
     * @return
     * @throws Exception
     */
    @Override
    public SendResult sendSyncMsg(ProduceMessage produceMessage) throws Exception {
        Message msg = createMessage(produceMessage);
        SendResult result = defaultMQProducer.send(msg);
        this.logMsg(msg, result);
        return result;
    }

    /**
     * 异步发送 默认回调函数
     *@param produceMessage
     *@throws Exception
     */
    public void sendAsyncMsg(ProduceMessage produceMessage) throws Exception {
        Message msg = createMessage(produceMessage);
        defaultMQProducer.send(msg, rocketSendCallback);
        this.logMsg(msg);
    }

    /**
     * 发送单向消息
     * @param produceMessage
     * @throws Exception
     */
    @Override
    public void sendOneWayMsg(ProduceMessage produceMessage) throws Exception {
        Message msg = createMessage(produceMessage);
        defaultMQProducer.sendOneway(msg);
        defaultMQProducer.send(msg, (queues, message, queNum) -> {
            int queueNum = Integer.parseInt(queNum.toString());
            return queues.get(queueNum);
        }, 0);
        this.logMsg(msg);
    }

    /**
     * 发送事务消息
     * @param produceMessage
     * @return
     * @throws MQClientException
     */
    @Override
    public SendResult sendTransactionMsg(ProduceMessage produceMessage) throws MQClientException {
        Message msg = createMessage(produceMessage);
        SendResult sendResult = transactionMQProducer.sendMessageInTransaction(msg, null);
        this.logMsg(msg, sendResult);
        return sendResult;
    }

    /**
     * 发送顺序消息
     * @param produceMessage
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public SendResult sendMsgOrder(ProduceMessage produceMessage,int orderId) throws Exception {
        Message msg = createMessage(produceMessage);
        SendResult sendResult = defaultMQProducer.send(msg, (List<MessageQueue> mqs, Message message, Object arg) -> {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
                , orderId);
        this.logMsg(msg, sendResult);
        return sendResult;
    }

    /**
     * 打印消息topic等参数方便后续查找问题(单向发送的日志打印)
     */
    private void logMsg(Message message) {
        log.info("消息队列发送完成:topic={},tag={},msgId={}", message.getTopic(), message.getTags(), message.getKeys());
    }

    /**
     * 打印消息topic等参数方便后续查找问题（发送有返回结果的日志打印）
     */
    private void logMsg(Message message, SendResult sendResult) {
        log.info("消息队列发送完成:topic={},tag={},msgId={},sendResult={}", message.getTopic(), message.getTags(), message.getKeys(), sendResult);
    }

}
