package com.jeorgio.javava.thirdparty.zego;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@DubboComponentScan("com.jeorgio.javava.thirdparty.zego.service")
@EntityScan("com.jeorgio.javava.live.entity")
public class ZegoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZegoApiApplication.class, args);
	}

	@Bean
	public Executor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(32);
		executor.setQueueCapacity(1000);
		executor.setThreadNamePrefix("asyncInvoker-");
		executor.initialize();
		return executor;
	}
}
