package cn.javava.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String JAVAVA_RESOURCE_ID = "javava";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(JAVAVA_RESOURCE_ID)
            /*.authenticationEntryPoint(authenticationEntryPoint())
            .accessDeniedHandler(accessDeniedHandler())*/;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/**/*.js", "/**/*.css", "/**/*.html","/trades/*", "/**/*.json", "/**/*.png")
                .permitAll()
                .anyRequest().authenticated();
    }

    /*@Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        authenticationEntryPoint.setExceptionTranslator(webResponseExceptionTranslator());
        return authenticationEntryPoint;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        OAuth2AccessDeniedHandler oauth2AccessDeniedHandler = new OAuth2AccessDeniedHandler();
        oauth2AccessDeniedHandler.setExceptionTranslator(webResponseExceptionTranslator());
        return oauth2AccessDeniedHandler;
    }

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                return super.translate(e);
            }
        };
    }*/
}
