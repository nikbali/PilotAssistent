package com.mai.pilot_assistent.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateAircraftRequest {
    private String name;
    private Integer year;
    private Double length;
    private Double wingspan;
    private Double height;
    private Double cruisingSpeed;
    private Double maxSpeed;
    private Double enginePower;
}
