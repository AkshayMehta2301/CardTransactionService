package com.experiment.service.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {

    @JsonProperty("id")
    @Schema(name = "id", example = "ef5625c1-dc1a-1234-9acf-e082b5501f5f",
        description = "Transaction id")
    private UUID id;

    @JsonProperty("account_id")
    @Schema(name = "account_id", example = "ef5625c1-dc1a-4db8-9acf-e082b5501f5f",
        description = "Account id")
    private UUID accountId;

    @JsonProperty("operation_type_id")
    @Schema(name = "operation_type_id", example = "ef5625c1-dc1a-4db8-1234-e082b5501f5f",
        description = "Operation type id")
    private UUID operationTypeId;

    @JsonProperty("amount")
    @Schema(name = "amount", example = "100", description = "Amount of transaction")
    private BigDecimal amount;
}
