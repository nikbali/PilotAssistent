package com.mai.pilot_assistent.controller;

import com.mai.pilot_assistent.controller.dto.CreateAirportsRequest;
import com.mai.pilot_assistent.controller.dto.base.ErrorResponse;
import com.mai.pilot_assistent.model.Airport;
import com.mai.pilot_assistent.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/airport")
public class AirportLoaderController {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportLoaderController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createAirports(@Valid @RequestBody List<CreateAirportsRequest> createAirportsRequest) {

        try {
            List<Airport> airportList = createAirportsRequest
                    .stream()
                    .map(CreateAirportsRequest::toAirport)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(airportRepository.saveAll(airportList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
                    .body(ErrorResponse.builder()
                            .errorText(e.getMessage())
                            .build());
        }


    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(airportRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
                    .body(ErrorResponse.builder()
                            .errorText(e.getMessage())
                            .build());
        }


    }
}
