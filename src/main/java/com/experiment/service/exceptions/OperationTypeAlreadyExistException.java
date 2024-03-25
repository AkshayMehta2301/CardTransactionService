package com.experiment.service.exceptions;

import static com.experiment.service.exceptions.ErrorCodes.OPERATION_TYPE_ALREADY_EXIST;
import static java.lang.String.format;

import com.experiment.service.enums.Operation;
import org.springframework.http.HttpStatus;

public class OperationTypeAlreadyExistException extends BusinessException {

    private static final String DEFAULT_MESSAGE = "OperationType: %s already exist.";

    public OperationTypeAlreadyExistException(Operation name) {
        super(HttpStatus.BAD_REQUEST, format(DEFAULT_MESSAGE, name.toString()),
            OPERATION_TYPE_ALREADY_EXIST.name());
    }
}
