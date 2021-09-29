//package com.balzzak.gatewayservice.handler;
//
//import com.balzzak.common.exception.BalzzakException;
//import com.balzzak.common.exception.ResponseError;
//import com.balzzak.common.exception.RequestException;
//import com.balzzak.common.exception.ResponseException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageConversionException;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.BindException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//import java.util.Date;
//
//@RestController
//@ControllerAdvice
//public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
//        ResponseException response = new ResponseException(new Date(), ex.getMessage(), request.getDescription(false));
//
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(RequestException.class)
//    public final ResponseEntity<?> handleRequestExceptions(RequestException ex, WebRequest request) {
//        // 401?? 404?? 등등 구분을 해야 한다면 그리고 validation에 대해서도
//        ex.handleRequestError();
//        ResponseException response = new ResponseException(new Date(), ex.getErrorMessage(), request.getDescription(false));
//
//        return new ResponseEntity<>(response, ex.getHttpStatus());
//    }
//
//    @ExceptionHandler(BalzzakException.class)
//    public final ResponseEntity<?> handleBalzzakException(BalzzakException ex, WebRequest request) {
//        ResponseError response = new ResponseError(ex.getErrorCode().name(), ex.getErrorMessage());
//        return new ResponseEntity<>(response, ex.getHttpStatus());
//    }
//
//
//    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
//    protected void handleHttpRequestValidationException(Exception ex, WebRequest request) {
//
//        this.handleRequestExceptions(new RequestException(null, ex.getMessage()), request);
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    protected void handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
//
//        this.handleRequestExceptions(new RequestException(null, ex.getMessage()), request);
//    }
//
//    @ExceptionHandler({HttpMessageConversionException.class, HttpMessageNotReadableException.class})
//    protected void handleInvalidHttpMessageBodyException(Exception ex, HttpServletRequest request) {
//
//        this.handleRequestExceptions(new RequestException(null, ex.getMessage()), (WebRequest) request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        ResponseException response = new ResponseException(new Date(), ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//}
