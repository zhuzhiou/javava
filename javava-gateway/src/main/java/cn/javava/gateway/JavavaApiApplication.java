package cn.javava.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class JavavaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavavaApiApplication.class, args);
    }

    @Bean
    AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
