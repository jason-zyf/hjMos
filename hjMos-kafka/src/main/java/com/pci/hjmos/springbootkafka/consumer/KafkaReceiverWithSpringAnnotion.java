package com.pci.hjmos.springbootkafka.consumer;

import com.pci.hjmos.springbootkafka.entity.MessageEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * 目前这种方式还不行 ，遗留问题
 */
@Component
public class KafkaReceiverWithSpringAnnotion {

    @EventListener(condition = "#event.msgs[0].topic()=='my-topic'")
    public void kafkaMsgListener(MessageEvent event) {
        try {
            ConsumerRecord msgs = event.getMsgs();
//          System.err.println("线程："+Thread.currentThread().getName()+"，消费主题"+msg.getTopic()+"消息:"+new String(msg.getBody())+ "--ID："+msg.getMsgId());
            System.out.println("通过spring注解方式，接受到消息："+ msgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
