package com.kissco.ex;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    String projectPath = "file:" + System.getProperty("user.home") + "/Documents/WEB/files/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        projectPath = projectPath.replace("\\", "/");
        System.out.println("projectPath = " + projectPath);
        registry.addResourceHandler("/**", "/files/**")
        .addResourceLocations("classpath:/static/", projectPath);

    }

}