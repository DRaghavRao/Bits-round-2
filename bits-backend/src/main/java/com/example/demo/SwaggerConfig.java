package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fullstack Courses API")
                        .version("1.0.0")
                        .description("API for managing fullstack courses")
                        .contact(new Contact()
                                .name("Dev-Team")
                                .url("https://www.dev-team.com/")
                                .email("dev-team@gmail.com")));
    }
}
