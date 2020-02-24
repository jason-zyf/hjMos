package com.pci.hjmos.mq.controller;

import com.pci.hjmos.mq.service.MQCallback;
import com.pci.hjmos.mq.service.ProduceMsgService;
import com.pci.hjmos.mq.util.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestRocketMqSendController {

    @Autowired
    private ProduceMsgService produceMsgService;

    @GetMapping("/index")
    public String index(){
        return "rocketIndex";
    }

    @GetMapping("/sendSyncMsg")
    public String sendSyncMsg(String topic,String content) throws Exception {

        log.info("发送一条消息...........");
        // 发送同步消息
        Result result = produceMsgService.sendSyncMsg(topic, content);

        return "发送一条同步消息："+content;
    }

    /**
     * 发送异步消息测试
     * @param topic   消息主题
     * @param content 消息内容
     * @return
     * @throws Exception
     */
    @GetMapping("/sendAsyncMsg")
    public String sendAsyncMsg(String topic,String content) throws Exception {

        // 发送异步消息
        produceMsgService.sendAsyncMsg(topic, content, new MQCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送异步消息成功，处理下一步业务");
            }
            @Override
            public void onException(Throwable e) {
                log.info("发送异步消息失败，抛出异常："+ e);
            }
        });

        return "发送一条异步消息："+content;
    }

    /**
     * 发送一条单向消息
     * @param topic
     * @param content
     * @return
     */
    @GetMapping("/sendOneWayMsg")
    public String sendOneWayMsg(String topic,String content) {

        log.info("发送一条消息...........");
        // 发送同步消息
        produceMsgService.sendOneWayMsg(topic, content);

        return "发送一条单向消息："+content;
    }


}
