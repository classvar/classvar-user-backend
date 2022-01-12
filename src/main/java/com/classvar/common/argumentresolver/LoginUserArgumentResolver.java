package com.classvar.common.argumentresolver;

import com.classvar.admin.application.dto.response.GetAdminInfoDto;
import com.classvar.common.SessionConst;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
    boolean hasType = GetAdminInfoDto.class.isAssignableFrom(parameter.getParameterType());

    return hasLoginAnnotation && hasType;
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory)
      throws Exception {

    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

    HttpSession session = request.getSession(false);

    if (session == null) {
      return null;
    }

    return session.getAttribute(SessionConst.SESSION_KEY_ADMIN);
  }
}
