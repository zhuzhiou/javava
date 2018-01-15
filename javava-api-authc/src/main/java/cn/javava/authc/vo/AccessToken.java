package cn.javava.authc.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AccessToken implements Serializable {

    private String access_token;

    private String refresh_token;

    private String openid;

    private Integer expires_in;

    private LocalDateTime expired_on;

    private Integer errcode;

    private String errmsg;

    @JsonProperty("access_token")
    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return refresh_token;
    }

    public void setRefreshToken(String refreshToken) {
        this.refresh_token = refreshToken;
    }

    @JsonProperty("openid")
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @JsonProperty("expires_in")
    public Integer getExpiresIn() {
        return expires_in;
    }

    public void setExpiresIn(Integer expires_in) {
        this.expires_in = expires_in;
    }

    @JsonIgnore
    public LocalDateTime getExpiredOn() {
        return expired_on;
    }

    public void setExpiredOn(LocalDateTime expired_on) {
        this.expired_on = expired_on;
    }

    @JsonProperty("errcode")
    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    @JsonProperty("errmsg")
    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
