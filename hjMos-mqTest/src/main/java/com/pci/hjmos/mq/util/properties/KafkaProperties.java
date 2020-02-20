package com.pci.hjmos.mq.util.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "spring.skafka")
@Primary
public class KafkaProperties {

    private String bootstrapServers;
    private Map<String,Object> producer = new HashMap<>();
    private Map<String,Object> consumer = new HashMap<>();

}
