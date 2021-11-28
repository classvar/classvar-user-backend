package com.classvar.common.config;

import com.classvar.common.argumentresolver.LoginUserArgumentResolver;
import com.classvar.common.argumentresolver.StudentArgumentResolver;
import com.classvar.common.interceptor.LoginCheckInterceptor;
import com.classvar.common.interceptor.StudentCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new LoginCheckInterceptor())
        .order(1)
        .addPathPatterns("/api")
        .excludePathPatterns("/api/signup")
        .excludePathPatterns("/api/login");
    registry
        .addInterceptor(new StudentCheckInterceptor())
        .order(1)
        .addPathPatterns("/api/answers/submit")
        .excludePathPatterns("/api/signup")
        .excludePathPatterns("/api/login")
        .excludePathPatterns("/api/students/**/join");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("http://localhost:3000", "https://classvar.com");
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new StudentArgumentResolver());
    resolvers.add(new LoginUserArgumentResolver());
  }
}
