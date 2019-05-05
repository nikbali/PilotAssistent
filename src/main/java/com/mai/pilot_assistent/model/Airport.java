package com.mai.pilot_assistent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Аэродром или аэропорт
 */
@Data
@Builder
@AllArgsConstructor
@Document(collection="airport")
public class Airport {
    @Id
    private String id;

    @Indexed(unique=true)
    private String airportIdApi;

    @Indexed(unique=true)
    private String nameAirport;

    @Indexed(unique=true)
    private String codeAirport;

    private String latitude;
    private String longitude;
    private String timezone;

}
