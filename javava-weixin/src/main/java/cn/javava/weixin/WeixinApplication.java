package cn.javava.weixin;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

import javax.xml.stream.XMLInputFactory;
import java.util.Arrays;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableOAuth2Sso
@EnableAspectJAutoProxy
public class WeixinApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(WeixinApplication.class, args);
	}

	@Bean
	public Executor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(1000);
		executor.setThreadNamePrefix("async-");
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

	@Bean
	public TimeBasedGenerator timeBasedGenerator() {
		return Generators.timeBasedGenerator(EthernetAddress.fromInterface());
	}

	@Bean
	public XMLInputFactory xmlInputFactory() {
		return XMLInputFactory.newFactory();
	}

	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint())
				.and()
				.authorizeRequests()
				.anyRequest()
				.authenticated();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		AuthenticationEntryPoint authenticationEntryPoint = new OAuth2ClientAuthenticationEntryPoint();
		return authenticationEntryPoint;
	}

	@Bean
	public Filter authenticationProcessingFilter() {
		AbstractAuthenticationProcessingFilter authenticationProcessingFilter = new OAuth2ClientAuthenticationProcessingFilter();
		return authenticationProcessingFilter;
	}*/
}
