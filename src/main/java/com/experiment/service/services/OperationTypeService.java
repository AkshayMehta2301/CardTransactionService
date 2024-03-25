package com.experiment.service.services;

import com.experiment.service.dtos.requests.CreateOperationTypeRequest;
import com.experiment.service.entities.OperationType;
import com.experiment.service.dtos.responses.OperationTypeResponse;
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
