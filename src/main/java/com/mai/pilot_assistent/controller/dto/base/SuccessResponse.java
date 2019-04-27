package com.mai.pilot_assistent.controller.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SuccessResponse {
    private Boolean success;
    private String message;

}
