package com.classvar.scoreservice.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
  private String message;
  private int status;
  private List<FieldErrorCustom> errors = new ArrayList<>();
  private String code;

  public ErrorResponse(ErrorCode invalidInputValue, BindingResult bindingResult) {
    this.status = invalidInputValue.getStatus();
    this.code = invalidInputValue.getCode();
    this.message = invalidInputValue.getMessage();
    for (FieldError e : bindingResult.getFieldErrors()) {
      this.errors.add(
          new FieldErrorCustom(e.getField(), e.getRejectedValue(), e.getDefaultMessage()));
    }
  }

  public ErrorResponse(ErrorCode invalidInputValue, String errorMessage) {
    this.status = invalidInputValue.getStatus();
    this.code = invalidInputValue.getCode();
    this.message = errorMessage;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class FieldErrorCustom { // validation 라이브러리에 이미 정의가 된게 있어 Custom 접미어 추가
    private String field;
    private String value;
    private String reason;

    public FieldErrorCustom(String field, Object rejectedValue, String defaultMessage) {
      this.field = field;
      this.value = (String) rejectedValue;
      this.reason = defaultMessage;
    }
  }
}
