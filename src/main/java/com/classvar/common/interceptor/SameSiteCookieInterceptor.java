package com.classvar.common.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class SameSiteCookieInterceptor implements HandlerInterceptor {
  final String sameSiteAttribute = "; SameSite=None";
  final String secureAttribute = "; Secure";

  /*
  Set-Cookie 헤더가 존재한다면 SameSite=None과 Secure를 추가한다.
   */
  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      @Nullable ModelAndView modelAndView) {

    Collection<String> setCookieHeaders = response.getHeaders(HttpHeaders.SET_COOKIE);

    if (setCookieHeaders == null || setCookieHeaders.isEmpty()) return;

    setCookieHeaders.stream()
        .filter(this::isNotBlank)
        .map(
            header -> {
              if (header.toLowerCase().contains("samesite")) {
                return header;
              } else {
                return header.concat(sameSiteAttribute);
              }
            })
        .map(
            header -> {
              if (header.toLowerCase().contains("secure")) {
                return header;
              } else {
                return header.concat(secureAttribute);
              }
            })
        .forEach(finalHeader -> response.setHeader(HttpHeaders.SET_COOKIE, finalHeader));
  }

  // is not empty (""), not null and not whitespace only.
  private boolean isNotBlank(String str) {
    return str != null && !str.trim().equals("");
  }
}
