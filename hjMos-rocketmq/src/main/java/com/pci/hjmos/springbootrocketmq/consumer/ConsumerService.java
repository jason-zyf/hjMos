package com.pci.hjmos.springbootrocketmq.consumer;

import com.pci.hjmos.springbootrocketmq.bean.MessageEvent;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsumerService {

    @EventListener(condition = "#event.msgs[0].topic=='my-topic'")
    public void receiveMessage(MessageEvent event){

        try {
            List<MessageExt> msgs = event.getMsgs();
            for (MessageExt msg : msgs) {
                System.err.println("线程："+Thread.currentThread().getName()+"，消费主题"
                        +msg.getTopic()+"消息:"+new String(msg.getBody())+ "--ID："+msg.getMsgId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventListener(condition = "#event.msgs[0].topic=='orderTopic'")
    public void receiveOrderMessage(MessageEvent event){

        try {
            List<MessageExt> msgs = event.getMsgs();
            for (MessageExt msg : msgs) {
                System.out.println("线程名称：【"+Thread.currentThread().getName()+"】，"+new String(msg.getBody()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
