package com.experiment.service.dtos.responses;

import com.experiment.service.enums.Operation;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationTypeResponse {

    @JsonProperty("id")
    @Schema(name = "id", example = "ef5625c1-dc1a-4db8-1234-e082b5501f5f",
        description = "Operation type id")
    private UUID id;

    @JsonProperty("name")
    @Schema(name = "name", oneOf = {Operation.class}, example = "WITHDRAWAL",
        description = "Type of operation")
    private Operation name;

    @JsonProperty("description")
    @Schema(name = "description",
        example = "Withdrawal operation which leads to deduction in account balance.",
        description = "Operation description")
    private String description;
}
