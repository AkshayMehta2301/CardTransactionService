package com.experiment.service.dtos.requests;

import com.experiment.service.enums.Operation;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOperationTypeRequest {

    @JsonProperty("name")
    @NotNull
    @Schema(name = "name", oneOf = {Operation.class}, example = "WITHDRAWAL",
        description = "Type of operation")
    private Operation name;

    @JsonProperty("description")
    @NotBlank
    @Schema(name = "description",
        example = "Withdrawal operation which leads to deduction in account balance.",
        description = "Operation description")
    private String description;
}
