package com.pci.hjmos.springbootkafka.service.impl;

import com.pci.hjmos.springbootkafka.constant.CodeConstant;
import com.pci.hjmos.springbootkafka.constant.MsgConstant;
import com.pci.hjmos.springbootkafka.entity.KafkaProduceMessage;
import com.pci.hjmos.springbootkafka.entity.Result;
import com.pci.hjmos.springbootkafka.entity.ResultData;
import com.pci.hjmos.springbootkafka.service.KafkaProduceMessageService;
import com.pci.hjmos.springbootkafka.service.MQCallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@Slf4j
public class KafkaProduceMessageServiceImpl implements KafkaProduceMessageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Producer<String, String> producer;

    @Value("${spring.skafka.bootstrap-servers}")
    private String bootstrapServers;

    @Override
    public boolean produceMessage(KafkaProduceMessage produceMessage) {
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(produceMessage.getTopic(), produceMessage.getContent());
        try {
            SendResult<String, String> SendResult = send.get();
            log.info("发送消息成功，"+SendResult.toString());
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 发送同步消息
     *
     * @param topic   消息主题
     * @param content 消息内容
     * @return 消息发送结果
     */
    @Override
    public Result sendSyncMsg(String topic, String content) throws Exception {
        ResultData data ;

        // 第一种使用kafkaProducer的方式
        ProducerRecord<String, String> record = new ProducerRecord<>(topic,"Kafka-demo-001", content);
        Future<RecordMetadata> sendp = producer.send(record);
        System.out.println("send" + sendp);
        System.out.println("offset:" + sendp.get().offset());
        System.out.println("hasOffset:" + sendp.get().hasOffset());

        // 第二种使用kafkaTemplate发送的方式
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, content);
        System.out.println("发送的kafka地址为："+bootstrapServers);
        System.out.println("回显："+send);
        System.out.println("回显："+send.get());
        data = new ResultData(topic, content,send.get().getProducerRecord().toString());

        return new Result(CodeConstant.RETCODE_200, MsgConstant.SUCCESS,data);
    }

    /**
     * 发送异步消息
     *
     * @param topic    消息主题
     * @param content  消息内容
     * @param callback 回调方法对象
     */
    @Override
    public void sendAsyncMsg(String topic, String content, MQCallback callback) throws Exception {
        ProducerRecord producerRecord = new ProducerRecord(topic, content);
        producer.send(producerRecord,callback);
    }

}
