package com.mai.pilot_assistent.controller.dto;

import com.mai.pilot_assistent.model.*;
import com.mai.pilot_assistent.util.converters.ConvertUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CreateFlightResponse {

    private String id;
    private Airport origin;
    private Airport destination;
    private String flightNumber;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private Aircraft aircraft;
    private FlightStatus status;
    private UserProfile pilot;

    public static CreateFlightResponse fromFlight(Flight flight){
        return CreateFlightResponse.builder()
                .id(flight.getId())
                .origin(flight.getOrigin())
                .destination(flight.getDestination())
                .departureDateTime(flight.getDepartureDateTime())
                .arrivalDateTime(flight.getArrivalDateTime())
                .aircraft(flight.getAircraft())
                .flightNumber(flight.getFlightNumber())
                .status(flight.getStatus())
                .pilot(UserProfile.builder()
                        .id(flight.getPilot().getId())
                        .username(flight.getPilot().getUsername())
                        .name(flight.getPilot().getName())
                        .email(flight.getPilot().getEmail())
                        .gender(flight.getPilot().getGender())
                        .birth(flight.getPilot().getBirth() != null ? ConvertUtils.toStringDate(flight.getPilot().getBirth()) : null)
                        .build())
                .build();
    }
}
