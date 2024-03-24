package com.test.service.services.transactionstrategy;

import com.test.service.dtos.requests.CreateTransactionRequest;
import com.test.service.dtos.responses.TransactionResponse;
import com.test.service.enums.Operation;

public interface ITransactionStrategy {

    /**
     * Create transaction
     *
     * @param request {@link CreateTransactionRequest}
     * @return {@link TransactionResponse}
     */
    TransactionResponse createTransaction(CreateTransactionRequest request);

    /**
     * Get type of operation
     *
     * @return {@link Operation}
     */
    Operation getOperation();
}
