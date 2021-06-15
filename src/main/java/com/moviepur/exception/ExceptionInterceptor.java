package com.moviepur.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MoviepurException.class)
  public final ResponseEntity<Object> handleAllExceptions(MoviepurException e) {
    return ResponseEntity.status(e.getCode()).body(e.getMessage());
  }
}
