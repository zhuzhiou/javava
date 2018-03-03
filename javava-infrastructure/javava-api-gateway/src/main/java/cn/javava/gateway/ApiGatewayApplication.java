package cn.javava.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
//@EnableAuthorizationServer
public class ApiGatewayApplication {// extends AuthorizationServerConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    /*@Bean
    AccessFilter accessFilter() {
        return new AccessFilter();
    }*/

    /*
    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
        clientDetailsServiceConfigurer
                .inMemory()
                .withClient("test")
                .secret("test")
                .authorizedGrantTypes("client_credentials")
                .scopes("app");
    }*/

    /*@Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }*/
}
