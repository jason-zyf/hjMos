package com.pci.hjmos.mq.service;

import com.pci.hjmos.mq.util.entity.Result;
import org.springframework.stereotype.Component;

@Component
public interface ProduceMsgService {

    /**
     * 发送同步消息
     * @param topic 消息主题
     * @param content 消息内容
     */
    Result sendSyncMsg(String topic, String content) throws Exception;

    /**
     * 发送异步消息
     * @param topic 消息主题
     * @param content 消息内容
     * @param callback 回调方法对象
     */
//    void sendAsyncMsg(String topic, String content, MQCallback callback) throws Exception;

}
