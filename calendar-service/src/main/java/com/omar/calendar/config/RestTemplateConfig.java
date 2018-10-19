package com.omar.calendar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean(name = "calendarRestTemplate")
    @Primary
    public RestTemplate getCustomRestTemplate(){
        RestTemplate template = new RestTemplate();

        RestTemplateErrorHandler errorHandler = new RestTemplateErrorHandler();
        template.setErrorHandler(errorHandler);

        return template;
    }
}

