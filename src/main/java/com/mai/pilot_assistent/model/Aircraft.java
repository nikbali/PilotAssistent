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
     * Фото
     */
    private String photo;

    /**
     * Высота
     */
    private Double height;

    /**
     * Крейсерская скорость
     */
    private Double cruising_speed;

    /**
     * Максимальная скорость
     */
    private Double max_speed;

    /**
     * Мощность двигателя (л.с)
     */
    private Double engine_power;
}
