package cn.javava.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EntityScan("cn.javava.pay.weixin")
@EnableDiscoveryClient
public class PayServicesMain {

    public static void main(String[] args) {
        SpringApplication.run(PayServicesMain.class, args);
    }

}
