package cn.javava.weixin.aspect;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.util.UriComponentsBuilder;

@Aspect
public class OAuth2ClientContextFilterAspect {

    @Pointcut("this(org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter) && target(builder) && call(* org.springframework.web.util.UriComponentsBuilder.queryParam(String, ..)) && args(name, values)")
    public void queryParamPoincut(UriComponentsBuilder builder, String name, Object... values) {}

    @Around("queryParamPoincut(builder, name, values)")
    public UriComponentsBuilder queryParamAround(UriComponentsBuilder builder, String name, Object... values) {
        builder.replaceQueryParam(name, values);
        return builder;
    }

    /*@Pointcut("this(org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter) && target(builder) && call(* build())")
    public void buildPoincut(UriComponentsBuilder builder) {}

    @Around("buildPoincut(builder)")
    public UriComponents buildAround(ProceedingJoinPoint pjp, UriComponentsBuilder builder) throws IOException {
        System.out.println(pjp.getSourceLocation());
        System.out.println(pjp.getThis());
        System.out.println(pjp.getTarget());
        return builder.build(true);
    }*/
}
