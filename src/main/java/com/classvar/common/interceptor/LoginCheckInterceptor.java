package com.classvar.common.interceptor;

import com.classvar.common.SessionConst;
import com.classvar.user.domain.UserRepository;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

  private UserRepository userRepository;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    HttpSession session = request.getSession();

    if (session == null || session.getAttribute(SessionConst.LOGIN_ID) == null) {
      // 로그인 실패
      return true;
    }
    return true;
  }
}
