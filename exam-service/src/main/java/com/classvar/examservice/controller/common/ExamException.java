package com.classvar.examservice.controller.common;

public class ExamException extends RuntimeException {
  private ErrorCode errorCode;

  public ExamException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public ExamException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return this.errorCode;
  }
}
