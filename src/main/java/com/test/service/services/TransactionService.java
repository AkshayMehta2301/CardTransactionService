package com.test.service.services;

import com.test.service.dtos.requests.CreateTransactionRequest;
import com.test.service.dtos.responses.TransactionResponse;

public interface TransactionService {

    /**
     * Create transaction
     *
     * @param request {@link CreateTransactionRequest}
     * @return {@link TransactionResponse}
     */
    TransactionResponse createTransaction(CreateTransactionRequest request);
}
