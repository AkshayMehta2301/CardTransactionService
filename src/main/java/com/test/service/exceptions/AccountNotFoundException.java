package com.test.service.exceptions;

import static com.test.service.exceptions.ErrorCodes.ACCOUNT_NOT_FOUND;
import static java.lang.String.format;

import java.util.UUID;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BusinessException {

    private static final String DEFAULT_MESSAGE = "Account with id: %s not found.";

    public AccountNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, format(DEFAULT_MESSAGE, id.toString()),
            ACCOUNT_NOT_FOUND.name());
    }
}
