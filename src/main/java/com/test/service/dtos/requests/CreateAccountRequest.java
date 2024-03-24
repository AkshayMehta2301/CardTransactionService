package com.test.service.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {

    @JsonProperty("document_number")
    @NotBlank
    @Schema(name = "document_number", example = "ABC-102011", minLength = 1, maxLength = 20,
        description = "Document number")
    private String documentNumber;
}
