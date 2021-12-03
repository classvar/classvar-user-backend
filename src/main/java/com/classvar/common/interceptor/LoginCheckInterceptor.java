package com.classvar.common.interceptor;

import com.classvar.common.SessionConst;
import com.classvar.error.exception.UnauthenticatedUserException;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (HttpMethod.OPTIONS.matches(request.getMethod())) {
      return true;
    }
    HttpSession session = request.getSession();

    // 미인증 사용자 요청
    if (session == null || session.getAttribute(SessionConst.LOGIN_ID) == null) {
      throw new UnauthenticatedUserException("미인증 사용자 입니다");
    }
    return true;
  }
}
