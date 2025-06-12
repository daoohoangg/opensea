package com.example.authservice.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    /*
    * mo ta api
    * http://localhost:8080/swagger-ui.html
    */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tên API của bạn")
                        .version("1.0.0")
                        .description("Mô tả API ở đây"));
    }
}
