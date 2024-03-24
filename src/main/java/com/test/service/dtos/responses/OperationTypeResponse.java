package com.test.service.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.service.enums.Operation;
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
    private UUID id;

    @JsonProperty("name")
    private Operation name;

    @JsonProperty("description")
    private String description;
}
