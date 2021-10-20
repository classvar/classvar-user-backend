package com.classvar.scoreservice.utils;

public class ScoreException extends RuntimeException {
  private ErrorCode errorCode;

  public ScoreException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public ScoreException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return this.errorCode;
  }
}
