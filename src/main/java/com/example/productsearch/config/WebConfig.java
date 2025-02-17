package com.example.productsearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // Specify your frontend URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public WebMvcConfigurer forwardToIndex() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // Перенаправление всех запросов, которые не соответствуют статическим файлам, на главный HTML файл
                registry.addViewController("/{spring:[a-zA-Z0-9-_]+}")
                        .setViewName("forward:/");
                registry.addViewController("/**/{spring:[a-zA-Z0-9-_]+}")
                        .setViewName("forward:/");
                registry.addViewController("/{spring:[a-zA-Z0-9-_]+}/**")
                        .setViewName("forward:/");
            }
        };
    }
}