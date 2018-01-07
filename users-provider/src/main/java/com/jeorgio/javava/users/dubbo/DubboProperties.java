package com.jeorgio.javava.users.dubbo;

import com.alibaba.dubbo.config.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.dubbo")
@Component
@lombok.Data
public class DubboProperties {

    private ApplicationConfig application;

    private RegistryConfig registry;

    private ProtocolConfig protocol;

    //private MonitorConfig monitor;

    private ProviderConfig provider;

    //private ModuleConfig module;

    //private MethodConfig method;

    //private ConsumerConfig consumer;
}
