package com.experiment.service.services;

import com.experiment.service.dtos.requests.CreateOperationTypeRequest;
import com.experiment.service.dtos.responses.OperationTypeResponse;
import com.experiment.service.entities.OperationType;
import java.util.List;
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


    /**
     * Get all operation type
     *
     * @return {@link List} of {@link OperationTypeResponse}
     */
    List<OperationTypeResponse> getAllOperationType();
}
