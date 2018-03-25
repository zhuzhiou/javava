package cn.javava.weixin.oauth2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class OAuth2AccessToken implements Serializable {

    private String value;

    private Date expiration;

    private String refreshToken;

    private String openid;

    private String scope;

    @JsonCreator
    public OAuth2AccessToken(
            @JsonProperty("access_token") String value,
            @JsonProperty("expires_in") int expires_in,
            @JsonProperty("refresh_token") String refresh_token,
            @JsonProperty("openid") String openid,
            @JsonProperty("scope") String scope) {
        this.value = value;
        this.refreshToken = refresh_token;
        this.openid = openid;
        this.scope = scope;
        this.expiration = new Date(System.currentTimeMillis() + (expires_in * 1000L));
    }

    @JsonProperty("access_token")
    public String getValue() {
        return value;
    }

    @JsonProperty("expires_in")
    public int getExpiresIn() {
        return expiration != null ? Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L).intValue() : 0;
    }

    @JsonIgnore
    public Date getExpiration() {
        return expiration;
    }

    @JsonIgnore
    public boolean isExpired() {
        return expiration != null && expiration.before(new Date());
    }

    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    @JsonProperty("openid")
    public String getOpenid() {
        return openid;
    }

    @JsonProperty("scope")
    public String getScope() {
        return scope;
    }
}
