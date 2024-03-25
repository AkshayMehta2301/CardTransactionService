package com.experiment.service;

import static com.experiment.service.configurations.ApplicationConstants.ACCOUNT_DOCUMENT_NUMBER_UNIQUE_KEY;
import static com.experiment.service.exceptions.ErrorCodes.ACCOUNT_ALREADY_EXIST;
import static com.experiment.service.exceptions.ErrorCodes.ACCOUNT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.experiment.service.dtos.requests.CreateAccountRequest;
import com.experiment.service.dtos.responses.AccountResponse;
import com.experiment.service.entities.Account;
import com.experiment.service.exceptions.AccountAlreadyExistException;
import com.experiment.service.exceptions.AccountNotFoundException;
import com.experiment.service.exceptions.BusinessException;
import com.experiment.service.repositories.AccountRepository;
import com.experiment.service.services.AccountService;
import com.experiment.service.services.impl.AccountServiceImpl;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CardTransactionRepositoryConfig.class)
public class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Captor
    ArgumentCaptor<Account> accountArgumentCaptor;

    private AccountService accountService;

    private static final UUID TEST_ID = UUID.randomUUID();
    private static final String TEST_DOCUMENT_NUMBER = "ABC-102100";
    private static final BigDecimal TEST_ZERO_BALANCE = BigDecimal.ZERO;
    private static final BigDecimal TEST_BALANCE = BigDecimal.TEN;
    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @BeforeEach
    public void setup() {
        accountService = new AccountServiceImpl(accountRepository);
    }

    @AfterEach
    public void teardown() {
        reset(accountRepository);
    }

    @Test
    public void shouldCreateAccount() {
        when(accountRepository.save(any(Account.class)))
            .thenAnswer(invocationOnMock -> {
                Account account = invocationOnMock.getArgument(0);
                account.setId(TEST_ID);
                return account;
            });

        AccountResponse accountResponse =
            accountService.createAccount(buildCreateAccountRequest());

        assertNotNull(accountResponse);
        verify(accountRepository, times(1))
            .save(accountArgumentCaptor.capture());
        assertCreatedAccount(accountArgumentCaptor.getValue());
        assertCreatedAccountResponse(accountResponse);
    }

    @Test
    public void shouldGetAccountById() {
        when(accountRepository.findById(any(UUID.class)))
            .thenAnswer(invocationOnMock -> {
                Account account = buildAccount(invocationOnMock.getArgument(0));
                return Optional.of(account);
            });

        Account account = accountService.getAccountById(TEST_ID);

        assertNotNull(account);
        verify(accountRepository, times(1)).findById(eq(TEST_ID));
        assertAccount(account);
    }

    @Test
    public void shouldThrowAccountNotFoundExceptionWhileGettingAccountById() {
        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        try {
            accountService.getAccountById(TEST_ID);
            fail("Expected AccountNotFound exception to be thrown");
        } catch (AccountNotFoundException e) {
            verify(accountRepository, times(1)).findById(eq(TEST_ID));
            assertEquals(NOT_FOUND, e.getHttpCode());
            assertEquals(ACCOUNT_NOT_FOUND.name(), e.getCode());
        }
    }

    @Test
    public void shouldSaveAccount() {
        when(accountRepository.save(any(Account.class)))
            .thenAnswer(invocationOnMock -> {
                Account account = invocationOnMock.getArgument(0);
                account.setId(TEST_ID);
                return account;
            });

        Account account = accountService.saveAccount(buildAccount(TEST_ID));

        assertNotNull(account);
        verify(accountRepository, times(1))
            .save(accountArgumentCaptor.capture());
        assertAccount(account);
        assertAccount(accountArgumentCaptor.getValue());
    }

    @Test
    public void shouldThrowAccountAlreadyExistExceptionWhileSavingAccount() {
        when(accountRepository.save(any(Account.class)))
            .thenThrow(
                new DataIntegrityViolationException(
                    "Dive exception",
                    new ConstraintViolationException(null, null, ACCOUNT_DOCUMENT_NUMBER_UNIQUE_KEY)
                )
            );

        try {
            accountService.saveAccount(buildAccount(TEST_ID));
            fail("Expected AccountAlreadyExistException to be thrown.");
        } catch (AccountAlreadyExistException e) {
            verify(accountRepository, times(1))
                .save(accountArgumentCaptor.capture());
            assertAccount(accountArgumentCaptor.getValue());
            assertEquals(BAD_REQUEST, e.getHttpCode());
            assertEquals(ACCOUNT_ALREADY_EXIST.name(), e.getCode());
        }
    }

    @Test
    public void shouldThrowBusinessExceptionWhileSavingAccount() {
        when(accountRepository.save(any(Account.class)))
            .thenThrow(new DataIntegrityViolationException("Dive exception", null));

        try {
            accountService.saveAccount(buildAccount(TEST_ID));
            fail("Expected BusinessException to be thrown.");
        } catch (BusinessException e) {
            verify(accountRepository, times(1))
                .save(accountArgumentCaptor.capture());
            assertAccount(accountArgumentCaptor.getValue());
        }
    }

    private CreateAccountRequest buildCreateAccountRequest() {
        return CreateAccountRequest.builder()
            .documentNumber(TEST_DOCUMENT_NUMBER)
            .build();
    }

    private Account buildAccount(UUID id) {
        return Account.builder()
            .id(id)
            .documentNumber(TEST_DOCUMENT_NUMBER)
            .balance(TEST_BALANCE)
            .build();
    }

    private void assertCreatedAccount(Account account) {
        assertEquals(TEST_DOCUMENT_NUMBER, account.getDocumentNumber());
        assertEquals(TEST_ZERO_BALANCE, account.getBalance());
    }

    private void assertCreatedAccountResponse(AccountResponse accountResponse) {
        assertEquals(TEST_ID, accountResponse.getId());
        assertEquals(TEST_DOCUMENT_NUMBER, accountResponse.getDocumentNumber());
        assertEquals(TEST_ZERO_BALANCE, accountResponse.getBalance());
    }

    private void assertAccount(Account account) {
        assertEquals(TEST_ID, account.getId());
        assertEquals(TEST_DOCUMENT_NUMBER, account.getDocumentNumber());
        assertEquals(TEST_BALANCE, account.getBalance());
    }
}
