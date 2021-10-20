package com.classvar.scoreservice.utils;

import lombok.Getter;

@Getter
public enum ErrorCode {
  // Common
  INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
  METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),

  // Score
  NO_SUCH_SCORE_ID(400, "S001", "No Such Score Id"),
  NO_SUCH_ANSWER_SHEET_ID(400, "S002", "No Such AnswerSheet Id");

  private final String code;
  private final String message;
  private int status;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }
}
