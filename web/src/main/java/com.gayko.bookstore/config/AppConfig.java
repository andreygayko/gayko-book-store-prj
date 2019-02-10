package com.gayko.bookstore.config;


import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@ComponentScan(basePackages = {
        "com.gayko.bookstore.config",
        "com.gayko.bookstore.dao",
        "com.gayko.bookstore.model",
        "com.gayko.bookstore.converters",
        "com.gayko.bookstore.service",
        "com.gayko.bookstore.controller",
})
@Configuration
@PropertySources({@PropertySource(value = "classpath:config.properties"),
        @PropertySource(value = "classpath:path.properties", ignoreResourceNotFound = true)})

public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }



}
