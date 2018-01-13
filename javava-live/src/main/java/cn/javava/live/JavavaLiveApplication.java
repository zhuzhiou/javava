package cn.javava.live;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan("cn.javava.live.entity")
@EnableAsync
@EnableSwagger2
public class JavavaLiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavavaLiveApplication.class, args);
	}

	@Bean
	public TimeBasedGenerator timeBasedGenerator() {
		return Generators.timeBasedGenerator(EthernetAddress.fromInterface());
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("加一联萌接口文档")
				.version("1.0")
				.contact(new Contact("朱志欧", "https://user.qzone.qq.com/18403796", "zhuzhiou@qq.com"))
				.license("unlicensed")
				.licenseUrl("http://unlicense.org")
				.build();
	}
}
