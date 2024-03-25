package com.experiment.service;

import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.mock;

import com.experiment.service.entities.Transaction;
import com.experiment.service.repositories.AccountRepository;
import com.experiment.service.repositories.OperationTypeRepository;
import com.experiment.service.repositories.TransactionRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CardTransactionRepositoryConfig {

    @Bean
    public TransactionRepository transactionRepository() {
        return mock(TransactionRepository.class, RETURNS_SMART_NULLS);
    }

    @Bean
    public AccountRepository accountRepository() {
        return mock(AccountRepository.class, RETURNS_SMART_NULLS);
    }

    @Bean
    public OperationTypeRepository operationTypeRepository() {
        return mock(OperationTypeRepository.class, RETURNS_SMART_NULLS);
    }
}
