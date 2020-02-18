package com.pci.hjmos.mq.controller;

import com.pci.hjmos.mq.service.ProduceMsgService;
import com.pci.hjmos.mq.util.entity.Result;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("sendSyncMsg")
    public String sendSyncMsg(String topic,String content) throws Exception {

        log.info("发送一条消息...........");
        // 发送同步消息
        Result result = produceMsgService.sendSyncMsg(topic, content);

        return "发送一条同步消息："+content;
    }


}
