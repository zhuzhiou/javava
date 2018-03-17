package com.infinitus.cs.emaster.qyweixin.accesstoken;

import java.io.Serializable;

/**
 * <p>
 *     微信客户端上下文
 * </p>
 *
 * @since 2018-02-05
 * @author zhuzhiou
 */
public class ComponentClientContext implements Serializable {

    private AccessToken accessToken;

    public ComponentClientContext() {
    }

    public ComponentClientContext(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

}
