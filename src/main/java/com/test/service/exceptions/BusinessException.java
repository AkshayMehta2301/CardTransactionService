package com.test.service.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private HttpStatusCode httpCode;
    private String code;
    private String detail;

    public BusinessException() {
        this((HttpStatusCode) HttpStatus.INTERNAL_SERVER_ERROR, (String)"An unexpected error occurred.");
    }

    public BusinessException(String message) {
        this((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR, (String)message);
    }

    public BusinessException(String message, Throwable cause) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(HttpStatusCode httpCode, String detail, String code) {
        this.httpCode = httpCode;
        this.code = code;
        this.detail = detail;
    }

    public BusinessException(HttpStatusCode httpCode, String detail, String code, Throwable cause) {
        super(detail, cause);
        this.httpCode = httpCode;
        this.code = code;
        this.detail = detail;
    }

    public BusinessException(HttpStatusCode httpCode, String detail, Throwable cause) {
        this(httpCode, detail, (String)null, cause);
    }

    public BusinessException(HttpStatusCode httpCode, String detail) {
        this(httpCode, detail, (String)null, (Throwable)null);
    }
}
