package com.pci.hjmos.redis.service;

import org.springframework.stereotype.Component;

@Component
public interface redisService {

    /**
     * put
     */
    public void set(String key, Object value);

    /**
     * get
     */
    public Object get(String key);


}
