package com.test.service.services.impl;

import com.test.service.dtos.requests.CreateTransactionRequest;
import com.test.service.dtos.responses.TransactionResponse;
import com.test.service.entities.OperationType;
import com.test.service.enums.Operation;
import com.test.service.services.OperationTypeService;
import com.test.service.services.TransactionService;
import com.test.service.services.transactionstrategy.TransactionStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final OperationTypeService operationTypeService;
    private final TransactionStrategyFactory transactionStrategyFactory;

    @Override
    public TransactionResponse createTransaction(CreateTransactionRequest request) {
        OperationType operationType =
            operationTypeService.getOperationTypeById(request.getOperationTypeId());
        Operation strategy = Operation.getMappedStrategy(operationType.getName());
        return transactionStrategyFactory.getTransactionStrategy(strategy)
            .createTransaction(request);
    }
}
