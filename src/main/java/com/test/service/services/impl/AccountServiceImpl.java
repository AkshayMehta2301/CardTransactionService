package com.test.service.services.impl;

import com.test.service.configurations.ApplicationConstants;
import com.test.service.dtos.requests.CreateAccountRequest;
import com.test.service.dtos.responses.AccountResponse;
import com.test.service.entities.Account;
import com.test.service.exceptions.AccountAlreadyExistException;
import com.test.service.exceptions.AccountNotFoundException;
import com.test.service.exceptions.BusinessException;
import com.test.service.repositories.AccountRepository;
import com.test.service.services.AccountService;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        Account account = Account.builder()
            .documentNumber(request.getDocumentNumber())
            .balance(BigDecimal.ZERO)
            .build();
        this.saveAccount(account);
        return AccountResponse.builder()
            .id(account.getId())
            .documentNumber(account.getDocumentNumber())
            .balance(account.getBalance())
            .build();
    }

    @Override
    @Transactional
    public Account getAccountById(UUID id) {
        return accountRepository.findById(id)
            .orElseThrow(() -> new AccountNotFoundException(id));
    }

    public Account saveAccount(Account account) {
        try {
            return accountRepository.save(account);
        } catch (DataIntegrityViolationException dive) {
            Throwable e = dive.getCause();
            if (e instanceof ConstraintViolationException) {
                String constraintName = ((ConstraintViolationException) e).getConstraintName();
                if (ApplicationConstants.ACCOUNT_DOCUMENT_NUMBER_UNIQUE_KEY.equals(constraintName)) {
                    throw new AccountAlreadyExistException(account.getDocumentNumber());
                } else {
                    throw new BusinessException(e.getCause());
                }
            } else {
                throw new BusinessException(dive.getCause());
            }
        }
    }
}
