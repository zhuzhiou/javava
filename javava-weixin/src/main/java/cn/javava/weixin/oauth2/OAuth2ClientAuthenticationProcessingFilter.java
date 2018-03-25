package cn.javava.weixin.oauth2;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuth2ClientAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private OAuth2RestTemplate oauth2RestTemplate;

    public OAuth2ClientAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/**"));
    }

    @Override
    public void afterPropertiesSet() {
        //不需要AuthenticationManager
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        OAuth2AccessToken accessToken;
        try {
            accessToken = oauth2RestTemplate.getAccessToken();
        } catch (OAuth2Exception e) {
            BadCredentialsException bad = new BadCredentialsException("Could not obtain access token", e);
            throw bad;
        }
        return null;
    }
}
