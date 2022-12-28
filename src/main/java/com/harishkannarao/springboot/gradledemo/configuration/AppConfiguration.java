package com.harishkannarao.springboot.gradledemo.configuration;

import com.harishkannarao.springboot.gradledemo.interceptor.SubjectRoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration {

    @Autowired
    private SubjectRoleInterceptor subjectRoleInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            public void addInterceptors(InterceptorRegistry registry) {
                // Interceptors are called in the same order it is added to the registry
                registry.addInterceptor(subjectRoleInterceptor);
            }
        };
    }
}
