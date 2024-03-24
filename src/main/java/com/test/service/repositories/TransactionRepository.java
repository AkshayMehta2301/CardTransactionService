package com.test.service.repositories;

import com.test.service.entities.Transaction;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Transaction save(Transaction transaction);
}
