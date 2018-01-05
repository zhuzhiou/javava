package com.jeorgio.javava.authc;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.Executor;

@SpringBootApplication
@EntityScan("com.jeorgio.javava.entity")
@EnableAsync
public class ThirdpartyAuthzApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThirdpartyAuthzApplication.class, args);
	}

	@Bean
	public TimeBasedGenerator timeBasedGenerator() {
		return Generators.timeBasedGenerator(EthernetAddress.fromInterface());
	}

	@Bean
	public Executor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(1000);
		executor.setThreadNamePrefix("AsyncMethod-");
		executor.initialize();
		return executor;
	}

	@Bean
	public RestTemplate restTemplate() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.ALL));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(Arrays.asList(converter));
		return restTemplate;
	}
}
