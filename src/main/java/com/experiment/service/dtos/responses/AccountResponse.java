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
public class AccountResponse {

    @JsonProperty("id")
    @Schema(name = "id", example = "ef5625c1-dc1a-4db8-9acf-e082b5501f5f",
        description = "Account id")
    private UUID id;

    @JsonProperty("document_number")
    @Schema(name = "document_number", example = "ABC-102011", description = "Document number")
    private String documentNumber;

    @JsonProperty("balance")
    @Schema(name = "balance", example = "0", description = "Account balance")
    private BigDecimal balance;
}
