package com.mai.pilot_assistent.controller.dto;

import com.mai.pilot_assistent.model.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateAirportsRequest {
    private String airportId;
    private String nameAirport;
    private String codeIataAirport;
    private String codeIcaoAirport;
    private String nameTranslations;
    private String latitudeAirport;
    private String longitudeAirport;
    private String geonameId;
    private String timezone;
    private String GMT;
    private String phone;
    private String nameCountry;
    private String codeIso2Country;
    private String codeIataCity;

    public Airport toAirport(){
        return Airport.builder()
                .airportIdApi(this.getAirportId())
                .nameAirport(this.nameAirport)
                .codeAirport(this.codeIataAirport)
                .latitude(this.latitudeAirport)
                .longitude(this.longitudeAirport)
                .timezone(this.timezone)
                .build();
    }
}
