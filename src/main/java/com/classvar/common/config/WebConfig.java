package com.classvar.common.config;

import com.classvar.common.argumentresolver.LoginUserArgumentResolver;
import com.classvar.common.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
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
        .addPathPatterns("/**")
        .excludePathPatterns("/api/signup")
        .excludePathPatterns("/api/login")
        .excludePathPatterns("/swagger-ui/**") // 스웨거 쪽은 제외
        .excludePathPatterns("/swagger-resources/**")
        .excludePathPatterns("/webjars/**")
        .excludePathPatterns("/v2/api-docs");
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new LoginUserArgumentResolver());
  }
}
