package com.test.service.controllers;

import com.test.service.dtos.requests.CreateOperationTypeRequest;
import com.test.service.dtos.requests.CreateTransactionRequest;
import com.test.service.dtos.responses.ErrorResponse;
import com.test.service.dtos.responses.OperationTypeResponse;
import com.test.service.dtos.responses.TransactionResponse;
import com.test.service.services.TransactionService;
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
@RequestMapping("/transactions")
@Validated
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(
        summary = "Create transaction.",
        description = "Create transaction for account and operation type.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = CreateTransactionRequest.class))),
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(
                responseCode = "400",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                responseCode = "404",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                responseCode = "500",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(
        @Valid @RequestBody CreateTransactionRequest request
    ) {
        return new ResponseEntity<>(transactionService.createTransaction(request), HttpStatus.OK);
    }
}
