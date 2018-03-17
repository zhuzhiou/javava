package com.infinitus.cs.emaster.qyweixin.accesstoken;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *     针对企业微信开发做了扩展
 * </p>
 *
 * @since 2018-02-06
 * @author zhuzhiou
 */
public class OAuth2RestTemplate extends RestTemplate implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(OAuth2RestTemplate.class);

    private AccessTokenProvider provider;

    private ResourceDetails resource;

    private OAuth2ClientContext context;

    public OAuth2RestTemplate(ResourceDetails resource) {
        this(resource, new AccessTokenProvider());
    }

    public OAuth2RestTemplate(ResourceDetails resource, AccessTokenProvider provider) {
        super(new ImmutableList.Builder<HttpMessageConverter<?>>().add(new MappingJackson2HttpMessageConverter()).build());

        this.context = new OAuth2ClientContext();
        this.provider = provider;
        this.resource = resource;
    }

    public void setOAuth2ClientContext(OAuth2ClientContext context) {
        this.context = context;
    }

    public ResourceDetails getResource() {
        return resource;
    }

    protected ClientHttpRequest createRequest(URI uri, HttpMethod method) throws IOException {
        // 在请求前获取 accessToken，后续的请求都附带上有效的 accessToken
        AccessToken accessToken = getAccessToken();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri);
        builder.replaceQueryParam("access_token", accessToken.getValue());
        uri = builder.build().toUri();
        return super.createRequest(uri, method);
    }

    public <T> T getForObject(String url, ParameterizedTypeReference<T> responseType, Object... uriVariables) {
        RequestCallback requestCallback = new AcceptHeaderRequestCallback();
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<>(responseType.getType(), getMessageConverters());
        return execute(url, HttpMethod.GET, requestCallback, responseExtractor, uriVariables);
    }

    public <T> T getForObject(String url, ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) {
        RequestCallback requestCallback = new AcceptHeaderRequestCallback();
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<>(responseType.getType(), getMessageConverters());
        return execute(url, HttpMethod.GET, requestCallback, responseExtractor, uriVariables);
    }

    public <T> T postForObject(String url, Object request, ParameterizedTypeReference<T> responseType) {
        Type javaType = responseType.getType();
        RequestCallback requestCallback = httpEntityCallback(request, javaType);
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor<>(javaType, getMessageConverters());
        return execute(url, HttpMethod.POST, requestCallback, responseExtractor);
    }


    /**
     * 优先从本地中取 accessToken ，如果本地没有 accessToken 或本地有 accessToken 但已失效，则从线上获取。
     */
    public AccessToken getAccessToken() {
        AccessToken accessToken = context.getAccessToken();

        if (accessToken == null || accessToken.isExpired()) {
            accessToken = acquireAccessToken(context);
        }
        return accessToken;
    }

    /**
     * 从线上获取 accessToken
     */
    protected AccessToken acquireAccessToken(OAuth2ClientContext clientContext) {
        AccessToken accessToken = provider.obtainAccessToken(resource);
        if (accessToken == null ) {
            if (accessToken != null && logger.isErrorEnabled()) {
                logger.error("AccessToken { errno: {}, errmsg: {} }", accessToken.getErrcode(), accessToken.getErrmsg());
            }
            throw new IllegalStateException(
                    "Access token provider returned a null access token, which is illegal according to the contract.");
        }
        clientContext.setAccessToken(accessToken);
        return accessToken;
    }

    /**
     * 如果 RestTemplate 提供acceptHeaderRequestCallback(Type type)签名，则不用新建这个类
     */
    private class AcceptHeaderRequestCallback implements RequestCallback {
        @Override
        public void doWithRequest(ClientHttpRequest request) {
            List<MediaType> allSupportedMediaTypes = Arrays.asList(MediaType.APPLICATION_JSON);
            request.getHeaders().setAccept(allSupportedMediaTypes);
        }
    }
}
