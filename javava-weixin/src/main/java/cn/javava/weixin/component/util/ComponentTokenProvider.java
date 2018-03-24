package cn.javava.weixin.component.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;

/**
 * <p>
 *     获取微信公众平台的 <b>AccessToken</b>
 * </p>
 *
 * @since 2018-02-05
 * @author zhuzhiou
 */
public class ComponentTokenProvider implements Serializable {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ComponentProperties componentProperties;

    @Autowired
    private RestTemplate restTemplate;

    public ComponentToken obtainAccessToken() {
        return retrieveToken(componentProperties);
    }

    protected ComponentToken retrieveToken(ComponentProperties componentProperties) {
        String accessTokenUri = componentProperties.getAccessTokenUri();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(accessTokenUri);
        builder.replaceQueryParam("appId", componentProperties.getAppId());
        builder.replaceQueryParam("appSecret", componentProperties.getAppSecret());
        UriComponents uriComponents = builder.build();
        ComponentToken accessToken = restTemplate.getForObject(uriComponents.toUri(), ComponentToken.class);
        return accessToken;
    }

}
