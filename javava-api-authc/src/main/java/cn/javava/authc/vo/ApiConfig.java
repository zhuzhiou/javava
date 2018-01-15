package cn.javava.authc.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "weixin.api")
@lombok.Data
@Component
public class ApiConfig {

    private String appId;

    private String appSecret;
}
