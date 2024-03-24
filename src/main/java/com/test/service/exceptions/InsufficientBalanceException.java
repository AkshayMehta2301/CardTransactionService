package com.test.service.exceptions;

import static com.test.service.exceptions.ErrorCodes.ACCOUNT_BALANCE_INSUFFICIENT;

import org.springframework.http.HttpStatus;

public class InsufficientBalanceException extends BusinessException {

    private static final String DEFAULT_MESSAGE =
        "Balance is in-sufficient to perform transaction.";

    public InsufficientBalanceException() {
        super(HttpStatus.BAD_REQUEST, DEFAULT_MESSAGE, ACCOUNT_BALANCE_INSUFFICIENT.name());
    }
}
