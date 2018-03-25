package cn.javava.weixin.oauth2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The OAuth 2 security context (for a specific user or client or combination thereof).
 *
 * @author Dave Syer
 */
public class OAuth2ClientContext implements Serializable {

    private AccessTokenRequest accessTokenRequest;

    private OAuth2AccessToken accessToken;

    private Map<String, Object> state = new HashMap<>();

    public AccessTokenRequest getAccessTokenRequest() {
        return accessTokenRequest;
    }

    public void setAccessTokenRequest(AccessTokenRequest accessTokenRequest) {
        this.accessTokenRequest = accessTokenRequest;
    }

    public OAuth2AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(OAuth2AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public void setPreservedState(String stateKey, Object preservedState) {
        state.put(stateKey, preservedState);
    }

    public Object removePreservedState(String stateKey) {
        return state.remove(stateKey);
    }

}
