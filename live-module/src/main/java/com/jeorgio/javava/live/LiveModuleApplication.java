package com.jeorgio.javava.live;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EntityScan("com.jeorgio.javava.live.entity")
@DubboComponentScan("com.jeorgio.javava.live.service")
@EnableAsync
public class LiveModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveModuleApplication.class, args);
	}

	@Bean
	public TimeBasedGenerator timeBasedGenerator() {
		return Generators.timeBasedGenerator(EthernetAddress.fromInterface());
	}
}
