package com.andis.prestamos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.andis.prestamos.interceptor.ConcurrencyInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ConcurrencyInterceptor concurrencyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(concurrencyInterceptor);
    }
}
