package com.mai.pilot_assistent.repository;

import com.mai.pilot_assistent.model.Aircraft;
import com.mai.pilot_assistent.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AircraftRepository extends MongoRepository<Aircraft, String> {

    /**
     * Поиск самолетов по наименованию
     * @param aircraftName название
     * @return список найденных самолетов, либо пустой список
     */
    List<Aircraft> findByName(String aircraftName);
}
