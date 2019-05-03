package com.mai.pilot_assistent.repository;

import com.mai.pilot_assistent.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository  extends MongoRepository<Flight, String> {
}
