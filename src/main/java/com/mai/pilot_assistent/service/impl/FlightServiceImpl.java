package com.mai.pilot_assistent.service.impl;

import com.mai.pilot_assistent.controller.dto.CreateFlightRequest;
import com.mai.pilot_assistent.model.Aircraft;
import com.mai.pilot_assistent.model.Flight;
import com.mai.pilot_assistent.model.FlightStatus;
import com.mai.pilot_assistent.model.User;
import com.mai.pilot_assistent.repository.FlightRepository;
import com.mai.pilot_assistent.security.CurrentUser;
import com.mai.pilot_assistent.security.UserPrincipal;
import com.mai.pilot_assistent.service.AircraftService;
import com.mai.pilot_assistent.service.FlightService;
import com.mai.pilot_assistent.service.UserService;
import com.mai.pilot_assistent.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

    final private FlightRepository flightRepository;
    private final UserService userService;
    private final AircraftService aircraftService;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, UserService userService, AircraftService aircraftService) {
        this.flightRepository = flightRepository;
        this.userService = userService;
        this.aircraftService = aircraftService;
    }

    @Override
    public Result<Flight> createFlight(@CurrentUser UserPrincipal userPrincipal, CreateFlightRequest request) {
        return createFlight(userPrincipal.getUsername(), request);
    }

    @Override
    public Result<Flight> createFlight(String username, CreateFlightRequest request) {
        return Result.retrieve(() -> {
            User user = userService.findByUsername(username).getValue();
            Aircraft aircraft = aircraftService.findById(request.getAircraftId()).getValue();
            Flight flight = Flight.builder()
                    .aircraft(aircraft)
                    .pilot(user)
                    .destination(request.getDestination())
                    .origin(request.getOrigin())
                    .departureDateTime(request.getDepartureDateTime())
                    .arrivalDateTime(request.getArrivalDateTime())
                    .status(FlightStatus.SCHEDULED)
                    .build();
            return flightRepository.save(flight);
        });
    }

    @Override
    public Result<Flight> createFlight(User user, CreateFlightRequest request) {
        return createFlight(user.getUsername(), request);
    }
}
