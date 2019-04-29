package com.mai.pilot_assistent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Entity - class Самолет
 */
@Data
@Builder
@AllArgsConstructor
public class Aircraft {

    @Id
    private String id;

    /**
     * Полное название
     */
    private String name;

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
