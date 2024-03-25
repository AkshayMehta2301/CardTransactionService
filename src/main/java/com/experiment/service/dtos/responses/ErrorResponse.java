package com.experiment.service.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    @JsonProperty("error_code")
    @Schema(name = "error_code", example = "INVALID_INPUT_REQUEST", description = "Error code")
    private String errorCode;

    @JsonProperty("error_detail")
    @Schema(name = "error_detail", example = "{name=must not be null}",
        description = "Error detail")
    private String errorDetail;
}
