package com.test.service.services.transactionstrategy;

import com.test.service.dtos.requests.CreateTransactionRequest;
import com.test.service.dtos.responses.TransactionResponse;
import com.test.service.entities.Account;
import com.test.service.entities.Transaction;
import com.test.service.enums.Operation;
import com.test.service.repositories.TransactionRepository;
import com.test.service.services.AccountService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditVoucherStrategy implements ITransactionStrategy {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse createTransaction(CreateTransactionRequest request) {
        Account account = accountService.getAccountById(request.getAccountId());
        Transaction transaction = Transaction.builder()
            .accountId(request.getAccountId())
            .operationTypeId(request.getOperationTypeId())
            .amount(request.getAmount())
            .build();
        transactionRepository.save(transaction);
        BigDecimal updatedBalance = account.getBalance().add(request.getAmount());
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
        return Operation.CREDIT_VOUCHER;
    }
}