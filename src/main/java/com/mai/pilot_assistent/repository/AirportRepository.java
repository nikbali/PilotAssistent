package com.mai.pilot_assistent.repository;

import com.mai.pilot_assistent.model.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends MongoRepository<Airport, String> {

    /**
     * Поиск аэродрома по названию
     * @param name название
     */
    Optional<Airport> findByNameAirport(String name);

    /**
     * Поиск аэродрома по названию
     * @param id название
     */
    Optional<Airport> findById(String id);
}
