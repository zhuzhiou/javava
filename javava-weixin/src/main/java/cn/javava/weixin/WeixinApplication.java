package cn.javava.weixin;

import cn.javava.weixin.oauth2.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import javax.xml.stream.XMLInputFactory;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Executor;

@SpringBootApplication
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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.addFilterBefore(authenticationProcessingFilter(oauth2RestTemplate()), BasicAuthenticationFilter.class)
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
	public CloseableHttpClient httpClient() {
		return HttpClients.createDefault();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Autowired
	private OAuth2ClientContext oauth2ClientContext;

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate() {
		return new OAuth2RestTemplate(oauth2ClientContext, httpClient(), objectMapper());
	}

	@Bean
	public Filter authenticationProcessingFilter(OAuth2RestTemplate oauth2RestTemplate) {
		AbstractAuthenticationProcessingFilter authenticationProcessingFilter = new OAuth2ClientAuthenticationProcessingFilter(oauth2RestTemplate);
		return authenticationProcessingFilter;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
	protected AccessTokenRequest accessTokenRequest(@Value("#{request.parameterMap}") Map<String, String[]> parameters) {
		AccessTokenRequest request = new AccessTokenRequest(parameters);
		return request;
	}

	@Autowired
	private AccessTokenRequest accessTokenRequest;

	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public OAuth2ClientContext oauth2ClientContext() {
		return new OAuth2ClientContext(accessTokenRequest);
	}
}
