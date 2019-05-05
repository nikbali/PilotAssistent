package com.mai.pilot_assistent.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
public class CreateAircraftRequest {
    private String name;
    private String registrationName;
    private String baseAirportId;
    private Integer year;
    private Double length;
    private Double wingspan;
    private Double height;
    private Double cruisingSpeed;
    private Double maxSpeed;
    private Double enginePower;
    private MultipartFile image;
}
