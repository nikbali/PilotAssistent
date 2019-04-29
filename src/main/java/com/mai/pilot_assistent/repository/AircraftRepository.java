package com.mai.pilot_assistent.repository;

import com.mai.pilot_assistent.model.Aircraft;
import com.mai.pilot_assistent.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AircraftRepository extends MongoRepository<Aircraft, String> {

    /**
     * Поиск самолета по наименованию
     * @param aircraftName
     * @return самолет, либо null в Optional
     */
    Optional<Aircraft> findByName(String aircraftName);
}
