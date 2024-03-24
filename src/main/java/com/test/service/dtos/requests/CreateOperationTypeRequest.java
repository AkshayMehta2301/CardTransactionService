package com.test.service.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.service.enums.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
