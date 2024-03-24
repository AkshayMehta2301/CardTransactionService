package com.test.service.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private UUID id;

    @JsonProperty("account_id")
    private UUID accountId;

    @JsonProperty("operation_type_id")
    private UUID operationTypeId;

    @JsonProperty("amount")
    private BigDecimal amount;
}
