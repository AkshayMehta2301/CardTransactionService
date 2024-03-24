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
public class AccountResponse {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("balance")
    private BigDecimal balance;
}
