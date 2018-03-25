package cn.javava.weixin.oauth2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AccessTokenRequest implements Serializable {

    private OAuth2AccessToken existingToken;

    private Object state;

    private String preservedState;

    private Map<String, String> parameters = new HashMap<>();

    public OAuth2AccessToken getExistingToken() {
        return existingToken;
    }

    public void setStateKey(String state) {
        parameters.put("state", state);
    }

    public String getStateKey() {
        return parameters.get("state");
    }

    public void setPreservedState(Object state) {
        this.state = state;
    }

    public Object getPreservedState() {
        return state;
    }
}
