package com.balzzak.gatewayservice.handler;

import com.balzzak.common.exception.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseError> handleException(Exception e) {
    log.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    log.error("handleException", e);
    log.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    return new ResponseEntity<>(new ResponseError("error-Test",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }


}
