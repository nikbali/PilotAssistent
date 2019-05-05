package com.mai.pilot_assistent.controller;


import com.mai.pilot_assistent.controller.dto.CreateFlightRequest;
import com.mai.pilot_assistent.controller.dto.CreateFlightResponse;
import com.mai.pilot_assistent.controller.dto.base.ErrorResponse;
import com.mai.pilot_assistent.security.CurrentUser;
import com.mai.pilot_assistent.security.UserPrincipal;
import com.mai.pilot_assistent.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class FlightsController {

    final private FlightService flightService;

    @Autowired
    public FlightsController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/flight/create")
    public ResponseEntity<?> createFlight(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody CreateFlightRequest flightRequest) {
        return flightService.createFlight(currentUser, flightRequest)
                .fold(
                        (flight) -> ResponseEntity.ok(CreateFlightResponse.fromFlight(flight)),

                        (failure) -> ResponseEntity.status(HttpStatus.METHOD_FAILURE)
                                .body(ErrorResponse.builder()
                                        .errorText(failure.getMessage())
                                        .build())
                );

    }

    //TODO написать метод getAll
}
