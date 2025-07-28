package com.example.aiservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Đường dẫn API cần mở CORS
                        .allowedOrigins("http://localhost:5173") // Frontend local dev
                        .allowedMethods("*") // GET, POST, PUT, DELETE, ...
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}

