package com.experiment.service.services.impl;

import com.experiment.service.services.TransactionService;
import com.experiment.service.dtos.requests.CreateTransactionRequest;
import com.experiment.service.dtos.responses.TransactionResponse;
import com.experiment.service.entities.OperationType;
import com.experiment.service.enums.Operation;
import com.experiment.service.services.OperationTypeService;
import com.experiment.service.services.transactionstrategy.TransactionStrategyFactory;
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
