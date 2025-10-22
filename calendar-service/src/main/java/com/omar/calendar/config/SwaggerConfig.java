package com.omar.calendar.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A class to configure the API documentation through OpenAPI (SpringDoc)
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 *
 * September 24, 2018
 * Updated: October 2025
 *
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api Documentation for Omar's Calendar Services.")
                        .version("0.0.1")
                        .description("Calendar API Services"));
    }

}
