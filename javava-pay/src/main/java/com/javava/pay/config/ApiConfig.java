package com.javava.pay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;

@PropertySource("classpath:application.yaml")
@Configuration
@lombok.Data
public class ApiConfig implements Serializable {

}
