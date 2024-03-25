package com.experiment.service.services.transactionstrategy;

import com.experiment.service.dtos.requests.CreateTransactionRequest;
import com.experiment.service.dtos.responses.TransactionResponse;
import com.experiment.service.entities.Account;
import com.experiment.service.entities.Transaction;
import com.experiment.service.enums.Operation;
import com.experiment.service.exceptions.InsufficientBalanceException;
import com.experiment.service.repositories.TransactionRepository;
import com.experiment.service.services.AccountService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WithdrawalStrategy implements ITransactionStrategy {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;

    private static final BigDecimal BIG_DECIMAL_NEGATIVE_ONE = BigDecimal.valueOf(-1);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse createTransaction(CreateTransactionRequest request) {
        Account account = accountService.getAccountById(request.getAccountId());
        if (account.getBalance().compareTo(request.getAmount()) < 0 ) {
            throw new InsufficientBalanceException();
        }

        Transaction transaction = Transaction.builder()
            .accountId(request.getAccountId())
            .operationTypeId(request.getOperationTypeId())
            .amount(request.getAmount().multiply(BIG_DECIMAL_NEGATIVE_ONE))
            .build();
        transactionRepository.save(transaction);

        BigDecimal updatedBalance = account.getBalance().subtract(request.getAmount());
        account.setBalance(updatedBalance);
        accountService.saveAccount(account);

        return TransactionResponse.builder()
            .id(transaction.getId())
            .accountId(transaction.getAccountId())
            .operationTypeId(transaction.getOperationTypeId())
            .amount(transaction.getAmount())
            .build();
    }

    @Override
    public Operation getOperation() {
        return Operation.WITHDRAWAL;
    }
}
