package com.test.service.controllers;

import com.test.service.dtos.requests.CreateOperationTypeRequest;
import com.test.service.dtos.responses.OperationTypeResponse;
import com.test.service.services.OperationTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation-types")
@Validated
@RequiredArgsConstructor
public class OperationTypeController {

    private final OperationTypeService operationTypeService;

    @PostMapping
    public ResponseEntity<OperationTypeResponse> createOperationType(@Valid @RequestBody
                                                                     CreateOperationTypeRequest request) {
        return new ResponseEntity<>(operationTypeService.createOperationType(request), HttpStatus.OK);
    }
}
