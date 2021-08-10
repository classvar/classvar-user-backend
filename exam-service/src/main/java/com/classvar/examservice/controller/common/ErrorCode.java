package com.classvar.examservice.controller.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
  // Common
  INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
  METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),

  // Exam
  NO_SUCH_EXAM_ID(400, "E001", "No Such Exam Id"),
  NO_SUCH_QUESTION_ID(400, "E002", "No Such Question Id"),
  NO_SUCH_ANNOUNCEMENT_ID(400, "E003", "No Such Announcement Id"),
  DELETE_EXAM_DENIED(400, "E004", "Delete Exam Denied"),
  DELETE_QUESTION_DENIED(400, "E005", "Delete Question Denied");

  private final String code;
  private final String message;
  private int status;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }
}
