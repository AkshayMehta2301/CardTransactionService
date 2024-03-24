package com.test.service.services;

import com.test.service.dtos.requests.CreateOperationTypeRequest;
import com.test.service.dtos.responses.OperationTypeResponse;
import com.test.service.entities.OperationType;
import java.util.UUID;

public interface OperationTypeService {

    /**
     * Create operation type
     *
     * @param request {@link CreateOperationTypeRequest}
     * @return {@link OperationTypeResponse}
     */
    OperationTypeResponse createOperationType(CreateOperationTypeRequest request);

    /**
     * Get operation type by id
     *
     * @param id {@link UUID}
     * @return {@link OperationType}
     */
    OperationType getOperationTypeById(UUID id);
}
