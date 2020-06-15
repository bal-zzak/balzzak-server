package com.balzzak.accountservice.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    protected String defaultException(HttpServletRequest request, Exception e) {
        return "Exception: " + e;
    }

}
