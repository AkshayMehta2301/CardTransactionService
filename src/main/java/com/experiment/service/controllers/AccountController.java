package com.experiment.service.controllers;

import com.experiment.service.dtos.requests.CreateAccountRequest;
import com.experiment.service.dtos.responses.AccountResponse;
import com.experiment.service.dtos.responses.ErrorResponse;
import com.experiment.service.entities.Account;
import com.experiment.service.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@Validated
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Operation(
        summary = "Create account",
        description = "Create account with zero balance.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = CreateAccountRequest.class))),
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountResponse.class))),
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
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.OK);
    }

    @Operation(
        summary = "Get account by Id",
        description = "Get account details based on the provided accountId.",
        parameters = @Parameter(name = "account_id", in = ParameterIn.PATH, required = true,
            example = "ef5625c1-dc1a-4db8-9acf-e082b5501f5e"),
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Account.class))),
            @ApiResponse(
                responseCode = "404",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @GetMapping("/{account_id}")
    public ResponseEntity<Account> getAccount(@PathVariable("account_id") UUID accountId) {
        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }
}
