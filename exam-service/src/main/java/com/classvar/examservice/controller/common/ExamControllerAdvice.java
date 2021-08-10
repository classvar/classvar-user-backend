package com.classvar.examservice.controller.common;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExamControllerAdvice {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Response> MethodArgumentNotValidAdvice(MethodArgumentNotValidException e) {
    final ErrorResponse response =
        new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ExamException.class)
  public ResponseEntity<Response> ExamAdvice(ExamException e) {
    final ErrorResponse response = new ErrorResponse(e.getErrorCode(), e.getMessage());
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }
}
