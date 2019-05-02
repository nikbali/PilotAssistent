package com.mai.pilot_assistent.controller;

import com.mai.pilot_assistent.controller.dto.CreateAircraftRequest;
import com.mai.pilot_assistent.controller.dto.base.ErrorResponse;
import com.mai.pilot_assistent.model.Aircraft;
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

    //TODO лютый костыль, нужно поменять на адвекватный json-body, но пока нет времени
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createAircraft(@RequestPart(value = "image", required = false) MultipartFile file,
                                            @RequestPart(value = "name", required = true) String name,
                                            @RequestPart(value ="year", required = false) String year,
                                            @RequestPart(value ="length", required = false) String length,
                                            @RequestPart(value ="wingspan", required = false) String wingspan,
                                            @RequestPart(value ="height", required = false) String height,
                                            @RequestPart(value ="cruisingSpeed", required = false) String cruisingSpeed,
                                            @RequestPart(value ="maxSpeed", required = false) String maxSpeed,
                                            @RequestPart(value ="enginePower", required = false) String enginePower){
        try {
            Aircraft aircraft = Aircraft.builder()
                    .name(name)
                    .year(year != null ? Integer.parseInt(year) : null)
                    .length(length != null ? Double.parseDouble(length) : null)
                    .height(height != null ? Double.parseDouble(height) : null)
                    .wingspan(wingspan != null ? Double.parseDouble(wingspan) : null)
                    .cruisingSpeed(cruisingSpeed != null ? Double.parseDouble(cruisingSpeed) : null)
                    .maxSpeed(maxSpeed != null ? Double.parseDouble(maxSpeed) : null)
                    .enginePower(enginePower != null ? Double.parseDouble(enginePower) : null)
                    .build();
            return aircraftService.createAircraft(file, aircraft)
                    .fold(
                            ResponseEntity::ok,

                            (fail) ->ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(ErrorResponse.builder()
                                            .errorText(fail.getMessage())
                                            .build())
                    );
        }catch (NumberFormatException ex){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body(ErrorResponse.builder()
                            .errorText("Некорректный тип данных в одном из параметров")
                            .build());
        }

    }

    @GetMapping(value = "/get_all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAll(){
        return aircraftService.findAll()
                .fold(
                        ResponseEntity::ok,

                        (fail) ->ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(ErrorResponse.builder().errorText(fail.getMessage()).build())
                );
    }


}
