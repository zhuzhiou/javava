package cn.javava.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class JavavaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavavaApiApplication.class, args);
    }


}
