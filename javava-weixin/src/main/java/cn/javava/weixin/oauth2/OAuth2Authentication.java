package cn.javava.weixin.oauth2;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OAuth2Authentication extends AbstractAuthenticationToken {

    public OAuth2Authentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
