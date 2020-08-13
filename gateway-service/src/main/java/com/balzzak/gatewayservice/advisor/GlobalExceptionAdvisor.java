package com.balzzak.gatewayservice.advisor;

import com.balzzak.data.exception.CommonErrorCode;
import com.balzzak.data.exception.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        printExceptionLog(ex);

        final String message = String.format("%s parameter is missing", ex.getParameterName());
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.MISSING_INPUT_VALUE, message, ex);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(errorResponseDto.getStatus()));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        printExceptionLog(ex);

        final String message = String.format("%s part is missing", ex.getRequestPartName());
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.MISSING_INPUT_VALUE, message, ex);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(errorResponseDto.getStatus()));
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        printExceptionLog(ex);

        final String message = String.format("%s variable is missing", ex.getVariableName());
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.MISSING_INPUT_VALUE, message, ex);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(errorResponseDto.getStatus()));
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        printExceptionLog(ex);

        final String message = String.format("%s should be of type %s", ex.getName(), ex.getRequiredType().getName());
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.MISMATCHING_TYPE_VALUE, message, ex);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(errorResponseDto.getStatus()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        printExceptionLog(ex);

        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.INVALID_INPUT_VALUE, "validation error", ex);
        errorResponseDto.addErrors(ex.getBindingResult());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(errorResponseDto.getStatus()));
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        printExceptionLog(ex);

        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.INVALID_INPUT_VALUE, "validation error", ex);
        errorResponseDto.addErrors(ex.getBindingResult());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(errorResponseDto.getStatus()));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        printExceptionLog(ex);

        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.INVALID_INPUT_VALUE, "validation error", ex);
        errorResponseDto.addErrors(ex.getConstraintViolations());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(errorResponseDto.getStatus()));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        printExceptionLog(ex);

        String message = String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL());
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.NO_HANDLER_FOUND, message, ex);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(errorResponseDto.getStatus()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        printExceptionLog(ex);

        headers.setAllow(Objects.requireNonNull(ex.getSupportedHttpMethods()));

        String message = String.format("HTTP method %s is not supported by this URL", ex.getMethod());
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.METHOD_NOT_ALLOWED, message, ex);
        return new ResponseEntity<>(errorResponseDto, headers, HttpStatus.valueOf(errorResponseDto.getStatus()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        printExceptionLog(ex);

        String supportedTypes = ex.getSupportedMediaTypes().stream()
                .map(MediaType::getType)
                .collect(Collectors.joining(" "));
        String message = String.format("%s media type is not supported. Supported media types are %s", ex.getContentType(), supportedTypes);
        final ErrorResponseDto errorResponseDto = ErrorResponseDto.of(CommonErrorCode.UNSUPPORTED_MEDIA_TYPE, message, ex);
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
