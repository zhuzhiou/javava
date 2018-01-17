package com.javava.thirdparty.weixin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@ConfigurationProperties(prefix = "wx.api")
@Configuration
@Data
public class ApiConfig implements Serializable {

    private String unifiedOrderUrl;
    private String tradeType;
    private String goodsBody;
    private String appId;
    private String mchId;
    private String key;
    private String notifyUrl;
    private String signType;
    private int httpConnectTimeout;
    private int httpReadTimeout;
    private String ip;

}
