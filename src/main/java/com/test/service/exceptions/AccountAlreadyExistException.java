package com.test.service.exceptions;

import static java.lang.String.format;

import org.springframework.http.HttpStatus;

public class AccountAlreadyExistException extends BusinessException{

    private static final String DEFAULT_MESSAGE = "Account with document number: %s already exist.";

    public AccountAlreadyExistException(String documentNumber) {
        super(HttpStatus.BAD_REQUEST, format(DEFAULT_MESSAGE, documentNumber),
            ErrorCodes.ACCOUNT_ALREADY_EXIST.name());
    }

}
