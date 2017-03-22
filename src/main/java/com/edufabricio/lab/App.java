package com.edufabricio.lab;

import org.apache.cxf.feature.LoggingFeature;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude={JmxAutoConfiguration.class,DataSourceAutoConfiguration.class})
@Import({
    OAuthConfig.class,
    JsonMapperConfig.class,
    RestEndpointConfig.class,
    SwaggerConfig.class
})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    @Bean
    public PropertyPlaceholderConfigurer placeHolder(){
        return new org.springframework.beans.factory.config.PropertyPlaceholderConfigurer();
    }

    @Bean
    public LoggingFeature loginFeature(){
        return new org.apache.cxf.feature.LoggingFeature();
    }


}