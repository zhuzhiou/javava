package cn.javava.weixin.oauth2;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AccessTokenRequest implements Serializable {

    private final MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

    private OAuth2AccessToken existingToken;

    private Object state;

    private String preservedState;

    public OAuth2AccessToken getExistingToken() {
        return existingToken;
    }

    public String getAuthorizationCode() {
        return getFirst("code");
    }

    public void setAuthorizationCode(String code) {
        parameters.set("code", code);
    }

    public String getStateKey() {
        return getFirst("state");
    }

    public void setStateKey(String state) {
        parameters.set("state", state);
    }

    public String getFirst(String key) {
        return parameters.getFirst(key);
    }

    public void setPreservedState(Object state) {
        this.state = state;
    }

    public Object getPreservedState() {
        return state;
    }
}
