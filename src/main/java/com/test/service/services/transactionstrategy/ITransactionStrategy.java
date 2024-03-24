package com.test.service.services.transactionstrategy;

import com.test.service.dtos.requests.CreateTransactionRequest;
import com.test.service.dtos.responses.TransactionResponse;
import com.test.service.entities.Account;
import com.test.service.enums.Operation;

public interface ITransactionStrategy {

    TransactionResponse createTransaction(CreateTransactionRequest request);

    Operation getOperation();
}
