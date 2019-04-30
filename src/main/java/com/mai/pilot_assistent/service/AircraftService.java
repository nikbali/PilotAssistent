package com.mai.pilot_assistent.service;

import com.mai.pilot_assistent.model.Aircraft;
import com.mai.pilot_assistent.util.Failure;
import com.mai.pilot_assistent.util.Result;

import java.util.List;

/**
 * Сервис для работы с "Самолетами"
 */
public interface AircraftService {

    /**
     * Метод ищет самолет по Id. Если самолет не найден, возвращает {@link Failure}
     * @param aircraftId Id самолета в mongo
     */
    Result<Aircraft> findById(String aircraftId);

    /**
     * Метод позвращает список самолетов по наименованию. Если ничего не найдено, возвращает {@link Failure}
     * @param name Id самолета в mongo
     */
    Result<List<Aircraft>> findByName(String name);


    /**
     * Метод создает новый самолет. Если в процессе создания произошла ошибка, возвращает {@link Failure} с инф-ей.
     * @param aircraft самолет
     */
    Result<Aircraft> createAircraft(Aircraft aircraft);

    /**
     * Метод обновляет существующий самолет. Если в процессе обновления произошла ошибка, возвращает {@link Failure} с инф-ей.
     * @param aircraft самолет
     */
    Result<Aircraft> updateAircraft(Aircraft aircraft);
}
