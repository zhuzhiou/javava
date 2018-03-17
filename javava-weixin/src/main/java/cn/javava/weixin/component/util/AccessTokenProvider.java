package com.infinitus.cs.emaster.qyweixin.accesstoken;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
public class AccessTokenProvider implements Serializable {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate;

    public AccessTokenProvider() {
        ImmutableList.Builder<HttpMessageConverter<?>> builder = new ImmutableList.Builder<HttpMessageConverter<?>>()
                .add(new MappingJackson2HttpMessageConverter());
        RestTemplate restTemplate = new RestTemplate(builder.build());
        //restTemplate.setRequestFactory(requestFactory);
        this.restTemplate = restTemplate;
    }

    public AccessTokenProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AccessToken obtainAccessToken(ResourceDetails resource) {
        return retrieveToken(resource);
    }

    protected AccessToken retrieveToken(ResourceDetails resource) {
        String accessTokenUri = resource.getAccessTokenUri();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(accessTokenUri);
        builder.replaceQueryParam("corpid", resource.getCorpId());
        builder.replaceQueryParam("corpsecret", resource.getCorpSecret());
        UriComponents uriComponents = builder.build();
        AccessToken accessToken = restTemplate.getForObject(uriComponents.toUri(), AccessToken.class);
        return accessToken;
    }

}
