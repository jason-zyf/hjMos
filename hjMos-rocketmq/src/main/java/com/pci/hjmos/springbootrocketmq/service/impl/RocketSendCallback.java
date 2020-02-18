package com.pci.hjmos.springbootrocketmq.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

@Slf4j
public class RocketSendCallback implements SendCallback {

    @Override
    public void onSuccess(SendResult sendResult) {
        log.info("send success,topic=" + sendResult.getMessageQueue().getTopic() +
                ", msgId=" + sendResult.getMsgId());
    }

    @Override
    public void onException(Throwable e) {
        log.error("send failed.", e);
    }

}
