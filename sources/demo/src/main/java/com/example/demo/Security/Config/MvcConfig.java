package com.example.demo.Security.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/react/build/static/");
        registry.addResourceHandler("/*.js")
                .addResourceLocations("/react/build/");
        registry.addResourceHandler("/*.json")
                .addResourceLocations("/react/build/");
        registry.addResourceHandler("/*.ico")
                .addResourceLocations("/react/build/");
        registry.addResourceHandler("/index.html")
                .addResourceLocations("/react/build/index.html");
    }
}