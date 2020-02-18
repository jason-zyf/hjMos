package com.pci.hjmos.springbootkafka.controller;

import com.pci.hjmos.springbootkafka.entity.KafkaProduceMessage;
import com.pci.hjmos.springbootkafka.entity.Result;
import com.pci.hjmos.springbootkafka.service.KafkaProduceMessageService;
import com.pci.hjmos.springbootkafka.service.MQCallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaSendController {

    @Autowired
    private KafkaProduceMessageService kafkaProduceMessageService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/sendKafka")
    public boolean createKafkaMessage(){

        String topic = "pci";
        String content = "hhhhjfds";

        KafkaProduceMessage produceMessage = new KafkaProduceMessage(topic,content);
        return kafkaProduceMessageService.produceMessage(produceMessage);
    }


    @GetMapping("/createKafkaSendSyncMsg")
    public Result createKafkaMessage(String topic, String content) throws Exception {
        return kafkaProduceMessageService.sendSyncMsg(topic,content);
    }

    @GetMapping("/createKafkaSendAsyncMsg")
    public boolean createKafkaMessageBatch(String topic,String content)throws Exception {
        kafkaProduceMessageService.sendAsyncMsg(topic,content,new MQCallback(){
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("异步消息发送完成");
            }
            @Override
            public void onException(Throwable e) {

                log.info("异步消息发送异常"+e);
            }
        });
        return true;
    }


}
