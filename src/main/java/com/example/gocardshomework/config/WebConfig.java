package com.example.gocardshomework.config;

import com.example.gocardshomework.api.controllers.interceptor.ConcurrencyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ConcurrencyInterceptor()).addPathPatterns("/api/v1/games/*");
  }
}
