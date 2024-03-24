package com.test.service.exceptionhandlers;

import com.test.service.dtos.responses.ErrorResponse;
import com.test.service.exceptions.BusinessException;
import com.test.service.exceptions.ErrorCodes;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleInvalidArgumentsExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.INVALID_INPUT_REQUEST.name(), StringUtils.join(new Map[]{errors}));
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getDetail());
        return new ResponseEntity<>(errorResponse, e.getHttpCode());
    }
}
