package cn.javava.weixin.oauth2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * TODO
 * 为避免与spring的OAuth2RestTemplate混淆，需重命名。
 */
public class OAuth2RestTemplate {

    private OAuth2ClientContext context;

    private CloseableHttpClient httpClient;

    private ObjectMapper objectMapper;

    /**
     * 尝试从上下文获取令牌，如果上下文无令牌或令牌失效，则重新获取令牌。
     * @return
     */
    public OAuth2AccessToken getAccessToken() {
        OAuth2AccessToken accessToken = context.getAccessToken();
        if (accessToken == null || accessToken.isExpired()) {
            acquireAccessToken(context);
        }
        return accessToken;
    }

    /**
     * 根据code，state获取令牌?
     */
    protected OAuth2AccessToken acquireAccessToken(OAuth2ClientContext oauth2Context) {

        AccessTokenRequest accessTokenRequest = oauth2Context.getAccessTokenRequest();
        if (accessTokenRequest == null) {
            throw new RuntimeException("AccessTokenRequiredException");
        }

        // Transfer the preserved state from the (longer lived) context to the current request.
        String stateKey = accessTokenRequest.getStateKey();
        if (stateKey != null) {
            accessTokenRequest.setPreservedState(oauth2Context.removePreservedState(stateKey));
        }

        /*OAuth2AccessToken existingToken = oauth2Context.getAccessToken();
        if (existingToken != null) {
            accessTokenRequest.setExistingToken(existingToken);
        }*/

        OAuth2AccessToken accessToken = null;
        accessToken = obtainAccessToken(accessTokenRequest);
        if (accessToken == null || accessToken.getValue() == null) {
            throw new IllegalStateException(
                    "Access token provider returned a null access token, which is illegal according to the contract.");
        }
        oauth2Context.setAccessToken(accessToken);
        return accessToken;
    }

    /**
     * 从微信公众平台获取令牌
     */
    private OAuth2AccessToken obtainAccessToken(AccessTokenRequest accessTokenRequest) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder.scheme("https").host("api.weixin.qq.com").port(80).path("/sns/oauth2/access_token");
        builder.queryParam("appid", "wxfe19480979014ade");
        builder.queryParam("secret", "");
        builder.queryParam("code", "code");
        builder.queryParam("grant_type", "snsapi_userinfo");
        // TODO
        builder.queryParam("state", accessTokenRequest.getPreservedState());

        HttpGet httpGet = new HttpGet(builder.toUriString());
        HttpEntityResponseExtractor<OAuth2AccessToken> responseExtractor = new HttpEntityResponseExtractor<>(objectMapper);

        OAuth2AccessToken accessToken = executeInternal(httpGet, responseExtractor);
        return accessToken;
    }

    /**
     * 访问微信网页授权的接口，并对结果反序列化。
     */
    private <T> T executeInternal(HttpUriRequest request, HttpEntityResponseExtractor<T> responseExtractor) {
        try {
            return httpClient.execute(
                    request,
                    responseExtractor);
        } catch (IOException e) {
            throw new ResourceAccessException("访问远程资源异常", e);
        }
    }

    @SuppressWarnings("unused")
    public <T> T getForObject(String uriString, Map<String, String> params, TypeReference<T> typeReference) {
        //获取令牌
        OAuth2AccessToken accessToken = getAccessToken();
        params.put("access_token", accessToken.getValue());
        //创建http请求
        URI uri = createUri(uriString, params);
        HttpGet httpGet = new HttpGet(uri);
        //获取结果
        HttpEntityResponseExtractor<T> responseExtractor = new HttpEntityResponseExtractor<>(objectMapper);
        return executeInternal(httpGet, responseExtractor);
    }

    @SuppressWarnings("unused")
    public <T> T postForObject(String uriString, Map<String, String> params, TypeReference<T> typeReference) {
        //获取令牌
        OAuth2AccessToken accessToken = getAccessToken();
        params.put("access_token", accessToken.getValue());
        //创建http请求
        URI uri = createUri(uriString, params);
        HttpPost httpPost = new HttpPost(uri);
        //获取结果
        HttpEntityResponseExtractor<T> responseExtractor = new HttpEntityResponseExtractor<>(objectMapper);
        return executeInternal(httpPost, responseExtractor);
    }

    private URI createUri(String uriString, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uriString);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                builder.replaceQueryParam(param.getKey(), param.getValue());
            }
        }
        return builder.build().toUri();
    }

    private static class HttpEntityResponseExtractor<T> extends AbstractResponseHandler<T> {

        private ObjectMapper objectMapper;

        public HttpEntityResponseExtractor(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public T handleEntity(HttpEntity httpEntity) throws IOException {
            return objectMapper.readValue(httpEntity.getContent(), new TypeReference<T>() {});
        }
    }
}
