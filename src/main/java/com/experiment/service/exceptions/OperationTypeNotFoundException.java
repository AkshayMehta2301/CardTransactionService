package com.experiment.service.exceptions;

import static com.experiment.service.exceptions.ErrorCodes.OPERATION_TYPE_NOT_FOUND;
import static java.lang.String.format;

import java.util.UUID;
import org.springframework.http.HttpStatus;

public class OperationTypeNotFoundException extends BusinessException {

    private static final String DEFAULT_MESSAGE = "OperationType with id: %s not found.";

    public OperationTypeNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, format(DEFAULT_MESSAGE, id.toString()),
            OPERATION_TYPE_NOT_FOUND.name());
    }
}
