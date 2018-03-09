package cn.javava.common.config;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    private static final String JAVAVA_RESOURCE_ID = "javava";

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("a")
                .resourceIds(JAVAVA_RESOURCE_ID)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("all")
                .authorities("client")
                .secret("b");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //endpoints.pathMapping("/oauth/token", "/token");
        //endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        //endpoints.tokenServices()
        endpoints.tokenEnhancer((OAuth2AccessToken accessToken, OAuth2Authentication authentication) -> {
            //可以对accessToken进行加工
            ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
            Map<String, Object> additionalInformation = accessToken.getAdditionalInformation();
            if (additionalInformation != null && !additionalInformation.isEmpty()) {
                builder.putAll(additionalInformation);
            }
            builder.put("code", 0);
            builder.put("message", "成功");

            DefaultOAuth2AccessToken accessToken_copy = new DefaultOAuth2AccessToken(accessToken);
            accessToken_copy.setAdditionalInformation(builder.build());
            return accessToken_copy;
        });
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }
}
