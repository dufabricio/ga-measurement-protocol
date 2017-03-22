package com.edufabricio.lab;

import com.edufabricio.lab.server.CustomObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.apache.cxf.staxutils.DocumentDepthProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class JsonMapperConfig {


    @Bean
    public DocumentDepthProperties depthProperties() {
        DocumentDepthProperties depthProperties = new DocumentDepthProperties();
        depthProperties.setInnerElementCountThreshold(500);
        return depthProperties;
    }

    @Bean
    public JSONProvider jsonProvider(@Qualifier("depthProperties") DocumentDepthProperties depthProperties) {
        JSONProvider provider = new JSONProvider();
        provider.setDepthProperties(depthProperties);
        return provider;
    }

    @Bean
    public CustomObjectMapper customObjectMapper() {
        CustomObjectMapper objectMapper = new CustomObjectMapper();
        return objectMapper;
    }

    @Bean
    public JacksonJsonProvider jacksonProvider(@Qualifier("customObjectMapper") CustomObjectMapper customObjectMapper) {
        JacksonJsonProvider jacksonProvider = new JacksonJsonProvider();
        jacksonProvider.setMapper(customObjectMapper);
        return jacksonProvider;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        return localValidatorFactoryBean;
    }



}