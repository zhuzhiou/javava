package cn.javava.weixin.oauth2;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuth2ClientAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException != null) {
            authException.printStackTrace();
        }
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder.scheme("https").host("open.weixin.qq.com").port(80).path("/connect/oauth2/authorize");
        builder.queryParam("appid", "wxfe19480979014ade");
        builder.queryParam("redirect_uri", "http://zhifu.javava.cn/zhifu/login");
        builder.queryParam("response_type", "code");
        builder.queryParam("scope", "snsapi_userinfo");
        builder.queryParam("state", "");
        builder.fragment("wechat_redirect");
        response.sendRedirect(builder.toUriString());
    }
}
