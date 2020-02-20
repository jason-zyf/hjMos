package com.pci.hjmos.mq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pci.hjmos.mq.service.ProduceMsgService;
import com.pci.hjmos.mq.util.constant.CodeConstant;
import com.pci.hjmos.mq.util.constant.MsgConstant;
import com.pci.hjmos.mq.util.entity.Result;
import com.pci.hjmos.mq.util.entity.ResultData;
import com.pci.hjmos.mq.util.properties.RocketMQProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

@Slf4j
@Service
//@EnableConfigurationProperties(RocketMQProperties.class)
@Primary
public class ProduceMsgServiceImpl implements ProduceMsgService {

    @Autowired
    private DefaultMQProducer rocketMqProducer;

    @Autowired
    private Producer<String, String> kafkaProducer;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // 直接从配置文件中获取
//    @Value("${rocketmq.namesrvAddr}")
//    private String namesrvAddr;

    // 先放在配置类中，从配置类中获取
    @Autowired
    private RocketMQProperties rocketMqProperties;

    @Override
    public Result sendSyncMsg(String topic, String content) throws Exception {

        ResultData data = new ResultData();
        try {
            String namesrvAddr = rocketMqProperties.getNamesrvAddr();
            if(!StringUtils.isEmpty(namesrvAddr)){
                Message msg = new Message(topic, topic, content.getBytes());
                SendResult sendResult = rocketMqProducer.send(msg);
                log.info("发送一条消息到rocketmq");
                data = new ResultData(topic, content,sendResult.getOffsetMsgId());
            }else {
                // 第一种方式，使用 kafkaProducer发送消息
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, content);
                RecordMetadata recordMetadata = kafkaProducer.send(record).get();
//                System.out.println("offset:"+recordMetadata.offset());    // 16
//                System.out.println("hasOffset:"+recordMetadata.hasOffset());  // true
                data = new ResultData(topic, content,String.valueOf(recordMetadata.offset()));

                // 第一种方式，使用 kafkaTemplate 发送消息
//                ListenableFuture<org.springframework.kafka.support.SendResult<String, String>> send =
//                        kafkaTemplate.send(topic, content);
//                System.out.println("发送一条消息到kafka，主题："+topic+",内容："+content+
//                        ",回显："+send.get().getProducerRecord().toString());
//                data = new ResultData(topic, content,send.get().getProducerRecord().toString());
            }
            return new Result(CodeConstant.RETCODE_200, MsgConstant.SUCCESS,data);
        }catch (Exception e){
            return new Result(CodeConstant.RETCODE_500, MsgConstant.ERROR,data);
        }
    }


}
