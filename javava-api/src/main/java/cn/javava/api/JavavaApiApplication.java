package cn.javava.api;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan("cn.javava.api")
public class JavavaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavavaApiApplication.class, args);
    }

    @Bean
    public MapperFacade mapperFacade() {
        DefaultMapperFactory.Builder builder = new DefaultMapperFactory.Builder();
        builder.mapNulls(false).useAutoMapping(true);
        return builder.build().getMapperFacade();
    }
}
