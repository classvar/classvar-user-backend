package com.classvar.common.config;

import com.classvar.common.argumentresolver.LoginUserArgumentResolver;
import com.classvar.common.interceptor.LoginCheckInterceptor;
import com.classvar.common.interceptor.SameSiteCookieInterceptor;
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
        .addPathPatterns("/**")
        .excludePathPatterns("/signup", "/login", "/logout")
        .excludePathPatterns(
            "/swagger-ui.html", "/v2/api-docs", "/swagger-resources/**", "/webjars/**");

    registry
        .addInterceptor(new SameSiteCookieInterceptor())
        .order(2)
        .addPathPatterns("/**");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
        .maxAge(-1) // add maxAge
        .allowCredentials(true)
        .allowedOriginPatterns(
            "https://classvar.com", "http://localhost:3000", "http://localhost:3001");
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new LoginUserArgumentResolver());
  }
}
