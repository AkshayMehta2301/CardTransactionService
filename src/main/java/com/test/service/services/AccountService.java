package com.test.service.services;

import com.test.service.dtos.requests.CreateAccountRequest;
import com.test.service.dtos.responses.AccountResponse;
import com.test.service.entities.Account;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest request);

    Account getAccountById(UUID id);

    Account saveAccount(Account account);
}
