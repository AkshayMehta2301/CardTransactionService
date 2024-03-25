package com.experiment.service.services.transactionstrategy;

import com.experiment.service.dtos.requests.CreateTransactionRequest;
import com.experiment.service.dtos.responses.TransactionResponse;
import com.experiment.service.enums.Operation;

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
