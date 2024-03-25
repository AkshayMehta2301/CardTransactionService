package com.experiment.service.services;

import com.experiment.service.dtos.requests.CreateAccountRequest;
import com.experiment.service.dtos.responses.AccountResponse;
import com.experiment.service.entities.Account;
import java.util.UUID;

public interface AccountService {

    /**
     * Create account with zero balance
     *
     * @param request {@link CreateAccountRequest}
     * @return {@link AccountResponse}
     */
    AccountResponse createAccount(CreateAccountRequest request);

    /**
     * Get account by provided id
     *
     * @param id {@link UUID}
     * @return {@link Account}
     */
    Account getAccountById(UUID id);

    /**
     * Update account
     *
     * @param account {@link Account}
     * @return {@link Account}
     */
    Account saveAccount(Account account);
}
