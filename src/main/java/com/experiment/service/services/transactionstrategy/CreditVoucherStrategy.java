package com.experiment.service.services.transactionstrategy;

import com.experiment.service.dtos.requests.CreateTransactionRequest;
import com.experiment.service.dtos.responses.TransactionResponse;
import com.experiment.service.entities.Account;
import com.experiment.service.entities.Transaction;
import com.experiment.service.enums.Operation;
import com.experiment.service.repositories.TransactionRepository;
import com.experiment.service.services.AccountService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditVoucherStrategy implements ITransactionStrategy {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;

    private static final BigDecimal BIG_DECIMAL_NEGATIVE_ONE = BigDecimal.valueOf(-1);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse createTransaction(CreateTransactionRequest request) {
        Account account = accountService.getAccountById(request.getAccountId());

        Transaction transaction = Transaction.builder()
            .accountId(request.getAccountId())
            .operationTypeId(request.getOperationTypeId())
            .amount(request.getAmount())
            .balance(request.getAmount())
            .build();
        transactionRepository.save(transaction);

        updateAllNegativeTransactions(transaction);

        BigDecimal updatedBalance = account.getBalance().add(request.getAmount());
        account.setBalance(updatedBalance);
        accountService.saveAccount(account);

        return TransactionResponse.builder()
            .id(transaction.getId())
            .accountId(transaction.getAccountId())
            .operationTypeId(transaction.getOperationTypeId())
            .amount(transaction.getAmount())
            .balance(transaction.getBalance())
            .build();
    }

    private void updateAllNegativeTransactions(Transaction transaction) {
        List<Transaction> negativeTransactions =
            transactionRepository.
                findByAccountIdAndBalanceLessThan(transaction.getAccountId(), BigDecimal.ZERO);
        BigDecimal remainingBalance = transaction.getBalance();

        for (Transaction negativeTransaction : negativeTransactions) {
            BigDecimal originalBalance = negativeTransaction.getBalance().multiply(BIG_DECIMAL_NEGATIVE_ONE);

            if (remainingBalance.compareTo(originalBalance) >= 0) {
                negativeTransaction.setBalance(BigDecimal.ZERO);
                remainingBalance = remainingBalance.subtract(originalBalance);
            } else {
                BigDecimal updateBalance = negativeTransaction.getBalance().add(remainingBalance);
                remainingBalance = BigDecimal.ZERO;
                negativeTransaction.setBalance(updateBalance);
            }

            if (remainingBalance.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }
        transaction.setBalance(remainingBalance);
        negativeTransactions.add(transaction);

        transactionRepository.saveAll(negativeTransactions);
    }

    @Override
    public Operation getOperation() {
        return Operation.CREDIT_VOUCHER;
    }
}
