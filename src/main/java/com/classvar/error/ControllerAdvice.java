package com.classvar.error;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Response> MethodArgumentNotValidAdvice(MethodArgumentNotValidException e) {
    final ErrorResponse response = new ErrorResponse(e.getBindingResult());
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Response> IllegalArgumentAdvice(IllegalArgumentException e) {
    final ErrorResponse response = new ErrorResponse(e.getMessage());
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }
}
