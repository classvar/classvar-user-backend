package com.classvar.common.interceptor;

import com.classvar.common.SessionConst;
import com.classvar.error.exception.UnauthenticatedUserException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StudentCheckInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    HttpSession session = request.getSession();

    // 미인증 응시자 요청
    if (session == null || session.getAttribute(SessionConst.STUDENT) == null) {
      throw new UnauthenticatedUserException("미인증 응시자 입니다");
    }
    return true;
  }
}
