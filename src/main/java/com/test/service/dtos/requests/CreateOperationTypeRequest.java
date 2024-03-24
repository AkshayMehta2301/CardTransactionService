package com.test.service.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.service.enums.Operation;
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
    private Operation name;

    @JsonProperty("description")
    @NotBlank
    private String description;
}
