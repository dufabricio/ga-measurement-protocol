package com.edufabricio.lab;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.edufabricio.lab.server.CustomObjectMapper;
import com.edufabricio.lab.server.ProductServiceRest;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class RestEndpointConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private CustomObjectMapper mapper;

    @Autowired
    private JacksonJsonProvider jacksonProvider;

    @Bean
    public ProductServiceRest productServiceRest() {
        return new ProductServiceRest();
    }

    @Bean
    public JAXRSServerFactoryBean product(@Qualifier("productServiceRest") ProductServiceRest productServiceRest) {
        JAXRSServerFactoryBean endpoint =
                buildServer("/product",productServiceRest);
        return endpoint;
    }

    private JAXRSServerFactoryBean buildServer(String address, Object serviceRest){
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();

        ArrayList providers = new ArrayList();
        providers.add(mapper);
        providers.add(jacksonProvider);

        endpoint.setBus(bus);
        endpoint.setAddress(address);
        endpoint.setServiceBeans(Arrays.<Object>asList(serviceRest));
        endpoint.setProviders(providers);
        //endpoint.setFeatures(Arrays.asList(new Swagger2Feature()));

        return endpoint;
    }

}
