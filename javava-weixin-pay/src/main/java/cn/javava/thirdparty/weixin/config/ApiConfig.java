package cn.javava.thirdparty.weixin.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
