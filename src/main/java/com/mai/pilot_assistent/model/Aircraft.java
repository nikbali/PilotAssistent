package com.mai.pilot_assistent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity - class Самолет
 */
@Data
@Builder
@AllArgsConstructor
@Document(collection="aircraft")
public class Aircraft {

    @Id
    private String id;

    /**
     * Полное название
     */
    private String name;

    /**
     * Регистрационный номер или бортовой номер
     */
    @Indexed(unique=true)
    private String registrationName;

    /**
     * Базовый аэродром
     */
    private Airport baseAirport;

    /**
     * Год производства
     */
    private Integer year;

    /**
     * Длина
     */
    private Double length;

    /**
     * Размах крыльев
     */
    private Double wingspan;

    /**
     * URL изображения
     */
    private String imageUrl;

    /**
     * Высота
     */
    private Double height;

    /**
     * Крейсерская скорость
     */
    private Double cruisingSpeed;

    /**
     * Максимальная скорость
     */
    private Double maxSpeed;

    /**
     * Мощность двигателя (л.с)
     */
    private Double enginePower;
}
