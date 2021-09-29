package com.balzzak.common.exception;

import com.balzzak.common.model.StatusCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BalzzakException extends RuntimeException {

    private StatusCode errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

    public BalzzakException(StatusCode errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
