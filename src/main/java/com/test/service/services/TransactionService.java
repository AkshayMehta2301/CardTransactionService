package com.test.service.services;

import com.test.service.dtos.requests.CreateTransactionRequest;
import com.test.service.dtos.responses.TransactionResponse;

public interface TransactionService {
    TransactionResponse createTransaction(CreateTransactionRequest request);
}
