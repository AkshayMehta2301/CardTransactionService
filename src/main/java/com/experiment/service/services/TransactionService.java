package com.experiment.service.services;

import com.experiment.service.dtos.requests.CreateTransactionRequest;
import com.experiment.service.dtos.responses.TransactionResponse;

public interface TransactionService {

    /**
     * Create transaction
     *
     * @param request {@link CreateTransactionRequest}
     * @return {@link TransactionResponse}
     */
    TransactionResponse createTransaction(CreateTransactionRequest request);
}
