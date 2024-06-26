package com.experiment.service.services.impl;

import com.experiment.service.configurations.ApplicationConstants;
import com.experiment.service.dtos.requests.CreateOperationTypeRequest;
import com.experiment.service.dtos.responses.OperationTypeResponse;
import com.experiment.service.entities.OperationType;
import com.experiment.service.exceptions.BusinessException;
import com.experiment.service.exceptions.OperationTypeAlreadyExistException;
import com.experiment.service.exceptions.OperationTypeNotFoundException;
import com.experiment.service.repositories.OperationTypeRepository;
import com.experiment.service.services.OperationTypeService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class OperationTypeServiceImpl implements OperationTypeService {

    private final OperationTypeRepository operationTypeRepository;

    @Override
    public OperationTypeResponse createOperationType(CreateOperationTypeRequest request) {
        OperationType operationType = OperationType.builder()
            .name(request.getName())
            .description(request.getDescription())
            .build();
        this.saveOperationType(operationType);
        return buildOperationTypeResponse(operationType);
    }

    private OperationTypeResponse buildOperationTypeResponse(OperationType operationType) {
        return OperationTypeResponse.builder()
            .id(operationType.getId())
            .name(operationType.getName())
            .description(operationType.getDescription())
            .build();
    }

    @Override
    public OperationType getOperationTypeById(UUID id) {
        return operationTypeRepository.findById(id)
            .orElseThrow(() -> new OperationTypeNotFoundException(id));
    }

    @Override
    public List<OperationTypeResponse> getAllOperationType() {
        List<OperationType> operationTypes = operationTypeRepository.findAll();
        List<OperationTypeResponse> operationTypeResponses = new ArrayList<>();
        if (!CollectionUtils.isEmpty(operationTypes)) {
            for (OperationType operationType : operationTypes) {
                operationTypeResponses.add(buildOperationTypeResponse(operationType));
            }
        }
        return operationTypeResponses;
    }

    private OperationType saveOperationType(OperationType operationType) {
        try {
            return operationTypeRepository.save(operationType);
        } catch (DataIntegrityViolationException dive) {
            Throwable e = dive.getCause();
            if (e instanceof ConstraintViolationException) {
                String constraintName = ((ConstraintViolationException) e).getConstraintName();
                if (ApplicationConstants.OPERATION_TYPE_NAME_UNIQUE_KEY.equals(constraintName)) {
                    throw new OperationTypeAlreadyExistException(operationType.getName());
                } else {
                    throw new BusinessException(e.getCause());
                }
            } else {
                throw new BusinessException(dive.getCause());
            }
        }
    }
}
