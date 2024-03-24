package com.test.service.repositories;

import com.test.service.entities.Account;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Account save(Account account);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    Optional<Account> findById(UUID id);
}
