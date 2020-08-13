package com.balzzak.data.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    private static final long serialVersionUID = -5273451723L;

    private LocalDateTime timestamp;
    private int status;
    private String code;
    private String message;
    private List<String> errors;
    private String debugMessage;

    private ErrorResponseDto(ErrorCode errorCode, String message, Exception ex) {
        this.timestamp = LocalDateTime.now();
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = message;
        this.errors = new ArrayList<>();
        this.debugMessage = ex.getLocalizedMessage();
    }

    public static ErrorResponseDto of(ErrorCode errorCode, String message, Exception ex) {
        return new ErrorResponseDto(errorCode, message, ex);
    }

    public void addErrors(BindingResult bindingResult) {
        for (FieldError error : bindingResult.getFieldErrors()) {
            this.errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : bindingResult.getGlobalErrors()) {
            this.errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
    }
}
