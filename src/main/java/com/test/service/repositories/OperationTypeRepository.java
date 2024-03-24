package com.test.service.repositories;

import com.test.service.entities.OperationType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepository extends JpaRepository<OperationType, UUID> {

    /**
     * Save operation type to database
     *
     * @param operationType {@link OperationType}
     * @return {@link OperationType}
     */
    OperationType save(OperationType operationType);

    /**
     * Get operation type by id
     *
     * @param id {@link UUID}
     * @return {@link Optional} of {@link OperationType}
     */
    Optional<OperationType> findById(UUID id);
}
