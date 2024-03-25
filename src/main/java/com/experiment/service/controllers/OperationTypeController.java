package com.experiment.service.controllers;

import com.experiment.service.dtos.requests.CreateOperationTypeRequest;
import com.experiment.service.dtos.responses.ErrorResponse;
import com.experiment.service.dtos.responses.OperationTypeResponse;
import com.experiment.service.services.OperationTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Operation(
        summary = "Get all operation type.",
        description = "Get all operation type. Operation type will be added by migration, " +
            "so with help of this APIs response, we can pass operationTypeId for various transactions.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))),
            @ApiResponse(
                responseCode = "500",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @GetMapping
    public ResponseEntity<List<OperationTypeResponse>> getAllOperationType() {
        return new ResponseEntity<>(operationTypeService.getAllOperationType(), HttpStatus.OK);
    }
}
