package com.mai.pilot_assistent.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CreateFlightRequest {
    private String originId;
    private String destinationId;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private String flightNumber;
    private String aircraftId;
}
