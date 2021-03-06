package cn.javava.weixin.authc.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "wx.api")
@lombok.Data
@Component
public class ApiConfig {

    private String appId;

    private String appSecret;
}
