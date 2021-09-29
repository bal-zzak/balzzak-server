package com.balzzak.common.exception;

import com.balzzak.common.model.StatusCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestException extends RuntimeException {

    private StatusCode errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

    public RequestException(StatusCode errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public RequestException(StatusCode errorCode, String errorMessage, HttpStatus httpStatus){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public void handleRequestError() {

    }
}
