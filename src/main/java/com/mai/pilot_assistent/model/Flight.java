package com.mai.pilot_assistent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * Entity - class Полет
 */
@Data
@Builder
@AllArgsConstructor
public class Flight {

    @Id
    private String id;

    /**
     * Пункт отправления
     */
    private Airport origin;

    /**
     * Пункт назначения
     */
    private Airport destination;

    /**
     * Время вылета(плановое)
     */
    private LocalDateTime departureDateTime;

    /**
     * Время вылета(фактическое)
     */
    private LocalDateTime departureFactTime;

    /**
     * Время прибытия (плановое)
     */
    private LocalDateTime arrivalDateTime;

    /**
     * Время прибытия (фактическое)
     */
    private LocalDateTime arrivalFactTime;

    /**
     * Статус рейса
     */
    private FlightStatus status;

    /**
     * Самолет
     */
    private Aircraft aircraft;

    /**
     * Номер рейса
     */
    private String flightNumber;

    /**
     * Пилот
     */
    private User pilot;
}
