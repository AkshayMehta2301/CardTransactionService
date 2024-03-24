package com.test.service.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {

    @JsonProperty("account_id")
    @NotNull
    private UUID accountId;

    @JsonProperty("operation_type_id")
    @NotNull
    private UUID operationTypeId;

    @JsonProperty("amount")
    @NotNull
    private BigDecimal amount;
}
