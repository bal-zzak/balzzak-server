package com.balzzak.gatewayservice.advisor;

import com.balzzak.data.exception.CommonErrorCode;
import com.balzzak.data.exception.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        printExceptionLog(ex);

        String message = String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL());
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.NO_HANDLER_FOUND, message, ex);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(errorResponseDto.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDto> handleException(Exception ex) {
        printExceptionLog(ex);

        String message = "Unexpected error";
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.INTERNAL_SERVER_ERROR, message, ex);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(errorResponseDto.getStatus()));
    }

    private void printExceptionLog(Exception ex) {
        log.info("{} has occurred!", ex.getClass().getName());
    }
}
