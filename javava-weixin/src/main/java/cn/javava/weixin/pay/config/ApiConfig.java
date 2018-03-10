package cn.javava.weixin.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@ConfigurationProperties(prefix = "wx.api")
@Configuration
@Data
public class ApiConfig implements Serializable {

    private String report;
    private String shortUrl;
    private String appId;
    private String mchId;
    private String key;
    private String signType;
    private int httpConnectTimeout;
    private int httpReadTimeout;
}
