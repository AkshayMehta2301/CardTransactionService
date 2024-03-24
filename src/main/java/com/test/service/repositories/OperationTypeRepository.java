package com.test.service.repositories;

import com.test.service.entities.OperationType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepository extends JpaRepository<OperationType, UUID> {

    OperationType save(OperationType operationType);

    Optional<OperationType> findById(UUID id);
}
