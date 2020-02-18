package com.pci.hjmos.springbootrocketmq.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMqProperties {

    private String namesrvAddr;
    private String producerGroupName;
    private String transactionProducerGroupName;
    private String producerInstanceName;
    private String producerTraninstanceName;
    private boolean enableHistoryConsumer;
    private List<Map<String,Object>> consumerList = new ArrayList<>();

}
