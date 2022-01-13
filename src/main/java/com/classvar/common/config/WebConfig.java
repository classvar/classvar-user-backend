package com.classvar.common.config;

import com.classvar.common.argumentresolver.LoginUserArgumentResolver;
import com.classvar.common.interceptor.LoginCheckInterceptor;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
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

  // SameSite=None을 추가하는 필터
  // SameSite=None 옵션은 HTTPS에서만 작동한다.
  // Secure 옵션도 지정돼야 하는데, Spring Boot에서 Session 설정이 기본으로 httpOnly, Secure = true로 되어있는 듯하다.
  @Bean
  public TomcatContextCustomizer sameSiteCookiesConfig() {
    return context -> {
      final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
      cookieProcessor.setSameSiteCookies(SameSiteCookies.NONE.getValue());
      context.setCookieProcessor(cookieProcessor);
    };
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new LoginUserArgumentResolver());
  }
}
