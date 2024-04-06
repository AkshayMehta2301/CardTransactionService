package com.experiment.service.repositories;

import com.experiment.service.entities.Transaction;
import jakarta.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    /**
     * Save transaction to database
     *
     * @param transaction {@link Transaction}
     * @return {@link Transaction}
     */
    Transaction save(Transaction transaction);

    /**
     * Find by account id and balance less than
     *
     * @param accountId {@link UUID}
     * @param amount {@link BigDecimal}
     * @return {@link List} of {@link Transaction}
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    List<Transaction> findByAccountIdAndBalanceLessThan(UUID accountId, BigDecimal amount);
}
