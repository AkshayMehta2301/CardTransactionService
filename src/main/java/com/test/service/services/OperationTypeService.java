package com.test.service.services;

import com.test.service.dtos.requests.CreateOperationTypeRequest;
import com.test.service.dtos.responses.OperationTypeResponse;
import com.test.service.entities.OperationType;
import java.util.UUID;

public interface OperationTypeService {

    OperationTypeResponse createOperationType(CreateOperationTypeRequest request);

    OperationType getOperationTypeById(UUID id);
}
