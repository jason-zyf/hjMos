package com.pci.hjMos.service.impl;

import com.pci.hjMos.service.Config;

public class RedisConfig implements Config {

    @Override
    public void initialize() {
        // 初始化实现
        System.out.println("redis实现初始化方法！！！！");
    }

}
