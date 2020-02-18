package com.pci.hjmos.springbootrocketmq.controller;

import com.pci.hjmos.springbootrocketmq.entity.OrderStep;
import com.pci.hjmos.springbootrocketmq.entity.ProduceMessage;
import com.pci.hjmos.springbootrocketmq.service.ProducerMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class RocketMqSendController {

    @Autowired
    private ProducerMessageService prodeucerMessageService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/sendMessage")
    public String sendMessage(String content){

        log.info("发送一条消息...........");
        prodeucerMessageService.produceMessage(new ProduceMessage("my-topic","aa",content));
        return "发送一个消息：" + content;
    }

    @GetMapping("/sendSyncMsg")
    public String sendSyncMsg() throws Exception {

        log.info("发送一条同步消息...........");
        prodeucerMessageService.sendSyncMsg(new ProduceMessage("my-topic","aa","hhh"));
        return "成功发送一条同步消息";
    }

    @GetMapping("/sendAsyncMsg")
    public String sendAsyncMsg() throws Exception {
        prodeucerMessageService.sendAsyncMsg(new ProduceMessage("my-topic","aa","async"));
        return "成功发送一条异步消息";
    }

    @GetMapping("/sendOneWayMsg")
    public String sendOneWayMsg() throws Exception {
        prodeucerMessageService.sendOneWayMsg(new ProduceMessage("my-topic","oneWay","oneway"));
        return "成功发送一条单向消息";
    }

    @GetMapping("/sendTransactionMsg")
    public String sendTransactionMsg() throws Exception {
        log.info("发送一条事务消息...........");
        prodeucerMessageService.sendTransactionMsg(new ProduceMessage("my-topic","aa","transaction"));
        return "成功发送一条事务消息";
    }

    @GetMapping("/sendMsgOrder")
    public String sendMsgOrder() throws Exception {
        log.info("发送顺序消息...........");

        List<OrderStep> orderSteps = OrderStep.buildOrdes();
        for (int i = 0; i < orderSteps.size(); i++) {
            String body = orderSteps.get(i)+"";
            System.out.println((int)orderSteps.get(i).getOrderId());
            prodeucerMessageService.sendMsgOrder(
                    new ProduceMessage("orderTopic","aa",body), (int)orderSteps.get(i).getOrderId());
        }
        return "成功发送顺序消息";
    }

}
