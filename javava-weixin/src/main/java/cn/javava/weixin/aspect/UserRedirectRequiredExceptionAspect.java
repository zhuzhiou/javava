package cn.javava.weixin.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Aspect
public class UserRedirectRequiredExceptionAspect {

    @Pointcut("call(org.springframework.security.oauth2.client.resource.UserRedirectRequiredException.new(..)) && args(redirectUri, requestParams)")
    public void constructPoincut(String redirectUri, Map<String, String> requestParams) {}

    @Before("constructPoincut(redirectUrl, requestParams)")
    public void constructBefore(ProceedingJoinPoint pjp, String redirectUrl, Map<String, String> requestParams) throws UnsupportedEncodingException {
        if (StringUtils.startsWithAny(redirectUrl, "https://open.weixin.qq.com")) {
            String client_id = requestParams.get("client_id");
            requestParams.remove("client_id");
            requestParams.put("appid", client_id);

//            String redirect_uri = requestParams.get("redirect_uri");
//            requestParams.remove("redirect_uri");
//            requestParams.put("redirect_uri", UriUtils.encode(redirect_uri, "UTF-8"));
        }
    }
}
