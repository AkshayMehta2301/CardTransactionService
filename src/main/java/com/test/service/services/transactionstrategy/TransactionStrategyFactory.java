package com.test.service.services.transactionstrategy;

import static java.lang.String.format;

import com.test.service.enums.Operation;
import com.test.service.exceptions.BusinessException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionStrategyFactory {

    private final Map<Operation, ITransactionStrategy> transactionStrategyMap;

    private static final String TRANSACTION_STRATEGY_NOT_FOUND = "Transaction strategy for: %s not found";

    @Autowired
    public TransactionStrategyFactory(List<ITransactionStrategy> transactionStrategies) {
        transactionStrategyMap = new HashMap<>();
        for(ITransactionStrategy transactionStrategy : transactionStrategies) {
            transactionStrategyMap.put(transactionStrategy.getOperation(), transactionStrategy);
        }
    }

    public ITransactionStrategy getTransactionStrategy(Operation operation) {
        if (transactionStrategyMap.containsKey(operation)) {
            return transactionStrategyMap.get(operation);
        } else {
            log.error(format(TRANSACTION_STRATEGY_NOT_FOUND, operation));
            throw new BusinessException();
        }
    }
}
