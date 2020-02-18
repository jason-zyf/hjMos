package com.pci.hjmos.mq.consumer;

import com.pci.hjmos.mq.util.bean.MessageEvent;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 监听rocketmq消息进行消费
 */
@Component
public class RocketMqConsumer {

    @EventListener(condition = "#event.msgs[0].topic=='my-topic'")
    public void rocketmqMsgListener3(MessageEvent event) {
        try {
            List<MessageExt> msgs = event.getMsgs();
            for (MessageExt msg : msgs) {
//                System.err.println("线程："+Thread.currentThread().getName()+"，消费主题"+msg.getTopic()+"消息:"+new String(msg.getBody())+ "--ID："+msg.getMsgId());
                System.out.println("接受rocketmq到消息："+ new String(msg.getBody()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
