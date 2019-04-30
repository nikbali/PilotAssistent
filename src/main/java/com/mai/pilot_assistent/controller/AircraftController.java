package com.mai.pilot_assistent.controller;

import com.mai.pilot_assistent.controller.dto.CreateAircraftRequest;
import com.mai.pilot_assistent.controller.dto.base.ErrorResponse;
import com.mai.pilot_assistent.service.AircraftService;
import com.mai.pilot_assistent.util.converters.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    @Autowired
    public AircraftController( AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @PostMapping(value = "/create", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createAircraft(@RequestParam("image") MultipartFile file, @RequestBody CreateAircraftRequest request){
       return aircraftService.createAircraft(file, ConvertUtils.fromCreateAircraftRequestToAircraft(request))
                .fold(
                        ResponseEntity::ok,

                        (fail) ->ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                                .body(ErrorResponse.builder()
                                                                    .errorText(fail.getMessage())
                                                                    .build())
                );
    }


}
