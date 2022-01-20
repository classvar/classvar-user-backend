package com.classvar.error.exception;

import com.classvar.error.exception.policy.ApplicationExceptionPolicy;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionReason implements ApplicationExceptionPolicy {

  BEAN_PROPERTY_NOT_EXISTS("Property '%s' for object '%s' doesn't exists");

  private final String code = ApplicationExceptionReason.class.getSimpleName();
  private final String message;


}