package cn.javava.weixin.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.replace;

public class RedirectUserAspect2 {

    @Pointcut("this(org.springframework.security.web.RedirectStrategy) && target(response) && call(* sendRedirect(..)) && args(redirectUrl)")
    public void redirectToLoginPoincut(HttpServletResponse response, String redirectUrl) {}

    @Around("redirectToLoginPoincut(response, redirectUrl)")
    public void redirectToLoginAround(ProceedingJoinPoint pjp, HttpServletResponse response, String redirectUrl) throws IOException {
        if (StringUtils.startsWithAny(redirectUrl, "https://open.weixin.qq.com")) {
            redirectUrl = join(replace(redirectUrl, "client_id=", "appid="), "#wechat_redirect");
        }
        response.sendRedirect(redirectUrl);
    }
}
