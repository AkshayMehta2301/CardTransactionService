package com.experiment.service.transactionstrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.experiment.service.CardTransactionRepositoryConfig;
import com.experiment.service.dtos.requests.CreateTransactionRequest;
import com.experiment.service.dtos.responses.TransactionResponse;
import com.experiment.service.entities.Account;
import com.experiment.service.entities.Transaction;
import com.experiment.service.repositories.TransactionRepository;
import com.experiment.service.services.AccountService;
import com.experiment.service.services.transactionstrategy.CreditVoucherStrategy;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CardTransactionRepositoryConfig.class)
public class CreditVoucherStrategyTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Captor
    private ArgumentCaptor<Transaction> transactionArgumentCaptor;

    @Captor
    private ArgumentCaptor<Account> accountArgumentCaptor;

    @Mock
    private AccountService accountService;

    private CreditVoucherStrategy creditVoucherStrategy;

    private static final UUID TEST_ID = UUID.randomUUID();
    private static final UUID TEST_ACCOUNT_ID = UUID.randomUUID();
    private static final UUID TEST_OPERATION_TYPE_ID = UUID.randomUUID();
    private static final String TEST_DOCUMENT_NUMBER = "ABC-120211";
    private static final BigDecimal TEST_ACCOUNT_BALANCE = BigDecimal.valueOf(100D);
    private static final BigDecimal TEST_TRANSACTION_AMOUNT = BigDecimal.valueOf(5D);
    private static final BigDecimal TEST_FINAL_ACCOUNT_BALANCE = TEST_ACCOUNT_BALANCE.add(TEST_TRANSACTION_AMOUNT);

    @BeforeEach
    public void setup() {
        creditVoucherStrategy = new CreditVoucherStrategy(accountService, transactionRepository);
    }

    @AfterEach
    public void teardown() {
        reset(transactionRepository);
    }

    @Test
    public void shouldCreateTransaction() {
        when(accountService.getAccountById(any(UUID.class)))
            .thenAnswer(invocationOnMock -> buildAccount(invocationOnMock.getArgument(0)));
        when(transactionRepository.save(any(Transaction.class)))
            .thenAnswer(invocationOnMock -> {
               Transaction transaction = invocationOnMock.getArgument(0);
               transaction.setId(TEST_ID);
               return transaction;
            });

        TransactionResponse transactionResponse =
            creditVoucherStrategy.createTransaction(buildCreateTransactionRequest());

        assertNotNull(transactionResponse);
        verify(accountService, times(1))
            .getAccountById(eq(TEST_ACCOUNT_ID));
        verify(transactionRepository, times(1))
            .save(transactionArgumentCaptor.capture());
        verify(accountService, times(1))
            .saveAccount(accountArgumentCaptor.capture());
        assertTransaction(transactionArgumentCaptor.getValue());
        assertAccount(accountArgumentCaptor.getValue());
        assertTransactionResponse(transactionResponse);
    }

    private Account buildAccount(UUID id) {
        return Account.builder()
            .id(id)
            .documentNumber(TEST_DOCUMENT_NUMBER)
            .balance(TEST_ACCOUNT_BALANCE)
            .build();
    }

    private CreateTransactionRequest buildCreateTransactionRequest() {
        return CreateTransactionRequest.builder()
            .accountId(TEST_ACCOUNT_ID)
            .operationTypeId(TEST_OPERATION_TYPE_ID)
            .amount(TEST_TRANSACTION_AMOUNT)
            .build();
    }

    private void assertTransaction(Transaction transaction) {
        assertEquals(TEST_ACCOUNT_ID, transaction.getAccountId());
        assertEquals(TEST_OPERATION_TYPE_ID, transaction.getOperationTypeId());
        assertEquals(TEST_TRANSACTION_AMOUNT, transaction.getAmount());
    }

    private void assertAccount(Account account) {
        assertEquals(TEST_ACCOUNT_ID, account.getId());
        assertEquals(TEST_DOCUMENT_NUMBER, account.getDocumentNumber());
        assertEquals(TEST_FINAL_ACCOUNT_BALANCE, account.getBalance());
    }

    private void assertTransactionResponse(TransactionResponse transactionResponse) {
        assertEquals(TEST_ACCOUNT_ID, transactionResponse.getAccountId());
        assertEquals(TEST_OPERATION_TYPE_ID, transactionResponse.getOperationTypeId());
        assertEquals(TEST_TRANSACTION_AMOUNT, transactionResponse.getAmount());
    }
}
