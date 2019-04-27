package com.mai.pilot_assistent.controller.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    @JsonProperty("errorText")
    private String errorText;
}
