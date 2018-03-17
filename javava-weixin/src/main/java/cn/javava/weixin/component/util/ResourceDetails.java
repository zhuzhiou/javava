package com.infinitus.cs.emaster.qyweixin.accesstoken;

import java.io.Serializable;

/**
 * <p>
 *     一个受保护的资源有以下属性：
 * </p>
 * <dl>
 *     <dt>clientId</dt>
 *     <dd>OAuth 客户端标识</dd>
 *     <dt>clientSecret</dt>
 *     <dd>与资源有关的秘码</dd>
 *     <dt>accessTokenUri</dt>
 *     <dd>提供访问口令的统一资源标识符</dd>
 * </dl>
 *
 * @since 2018-02-05
 * @author zhuzhiou
 */
public class ResourceDetails implements Serializable {

    private String corpId;

    private String corpSecret;

    private String accessTokenUri;

    private ResourceDetails() {
    }

    private ResourceDetails(ResourceDetails resource) {
        this.corpId = resource.getCorpId();
        this.corpSecret = resource.getCorpSecret();
        this.accessTokenUri = resource.getAccessTokenUri();
    }

    public String getCorpId() {
        return corpId;
    }

    void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpSecret() {
        return corpSecret;
    }

    void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }

    public String getAccessTokenUri() {
        return accessTokenUri;
    }

    void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private ResourceDetails target;

        public Builder() {
            this.target = new ResourceDetails();
        }

        public Builder corpId(String corpId) {
            target.setCorpId(corpId);
            return this;
        }

        public Builder corpSecret(String corpSecret) {
            target.setCorpSecret(corpSecret);
            return this;
        }

        public Builder accessTokenUri(String accessTokenUri) {
            target.setAccessTokenUri(accessTokenUri);
            return this;
        }

        public ResourceDetails build() {
            return new ResourceDetails(target);
        }
    }
}
