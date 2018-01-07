package com.jeorgio.javava.users.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboProviderConfig {

    @Autowired
    private DubboProperties dubboProperties;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = dubboProperties.getApplication();
        if (applicationConfig == null) {
            applicationConfig = new ApplicationConfig();
        }
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = dubboProperties.getRegistry();
        if (registryConfig == null) {
            registryConfig = new RegistryConfig();
        }
        return registryConfig;
    }

    @Bean
    public ProtocolConfig requestProtocolConfig() {
        ProtocolConfig protocolConfig = dubboProperties.getProtocol();
        if (protocolConfig == null) {
            protocolConfig = new ProtocolConfig();
        }
        return protocolConfig;
    }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = dubboProperties.getProvider();
        if (providerConfig == null) {
            providerConfig = new ProviderConfig();
        }
        return providerConfig;
    }
}