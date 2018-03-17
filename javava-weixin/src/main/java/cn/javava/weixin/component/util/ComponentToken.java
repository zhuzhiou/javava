package cn.javava.weixin.component.util;

import java.io.Serializable;

public class ComponentToken implements Serializable {

    private Integer errcode;

    private String errmsg;

    private String value;

    private Date expiration;

    @JsonCreator
    public AccessToken(
            @JsonProperty("errcode") int errcode,
            @JsonProperty("errmsg") String errmsg,
            @JsonProperty("access_tocken") String access_tocken,
            @JsonProperty("expires_in") int expires_in) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.value = access_tocken;
        this.expiration = new Date(System.currentTimeMillis() + (expires_in * 1000));
    }

    @JsonProperty("errcode")
    public Integer getErrcode() {
        return errcode;
    }

    @JsonProperty("errmsg")
    public String getErrmsg() {
        return errmsg;
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
}
