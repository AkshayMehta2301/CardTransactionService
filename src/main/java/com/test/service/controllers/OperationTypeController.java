package com.test.service.controllers;

import com.test.service.dtos.requests.CreateOperationTypeRequest;
import com.test.service.dtos.responses.ErrorResponse;
import com.test.service.dtos.responses.OperationTypeResponse;
import com.test.service.services.OperationTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
        summary = "Create operation type.",
        description = "Create operation type.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = CreateOperationTypeRequest.class))),
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OperationTypeResponse.class))),
            @ApiResponse(
                responseCode = "400",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                responseCode = "500",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @PostMapping
    public ResponseEntity<OperationTypeResponse> createOperationType(@Valid @RequestBody
                                                                     CreateOperationTypeRequest request) {
        return new ResponseEntity<>(operationTypeService.createOperationType(request), HttpStatus.OK);
    }
}
