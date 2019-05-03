package com.mai.pilot_assistent.model;

public enum FlightStatus {

    /**
     * Запланирован
     */
    SCHEDULED,

    /**
     * Неоходимо пройти преполетный проверки
     */
    NEED_CHECK,

    /**
     * В процессе проверки
     */
    PROCESS_CHECK,

    /**
     * Готов к полету
     */
    READY,

    /**
     * В полете
     */
    FLYING,

    /**
     * Завершен
     */
    DONE,

    /**
     * Отменен
     */
    REJECTED,

}
