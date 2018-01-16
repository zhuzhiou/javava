package cn.javava.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("cn.javava.api")
public class JavavaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavavaApiApplication.class, args);
    }
}
