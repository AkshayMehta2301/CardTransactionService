package com.test.service.exceptions;

import static com.test.service.exceptions.ErrorCodes.OPERATION_TYPE_ALREADY_EXIST;
import static java.lang.String.format;

import com.test.service.enums.Operation;
import org.springframework.http.HttpStatus;

public class OperationTypeAlreadyExistException extends BusinessException {

    private static final String DEFAULT_MESSAGE = "OperationType: %s already exist.";

    public OperationTypeAlreadyExistException(Operation name) {
        super(HttpStatus.BAD_REQUEST, format(DEFAULT_MESSAGE, name.toString()),
            OPERATION_TYPE_ALREADY_EXIST.name());
    }
}
