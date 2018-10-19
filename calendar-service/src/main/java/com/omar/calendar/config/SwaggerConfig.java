package com.omar.calendar.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * A class to configure the API documentation through Swagger
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 *
 * September 24, 2018
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String BASE_PACKAGE = "com.omar.calendar.endpoint";

    @Bean
    public Docket customerServicesV1Api() {

        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any()).build().pathMapping("/").apiInfo(getApiInfo());

    }


    private static ApiInfo getApiInfo() {

        final ApiInfo apiInfo = new ApiInfo("Api Documentation for Omar's Calendar Services.", null, "0.0.1",
                null, null, null, null);
        return apiInfo;
    }

}
