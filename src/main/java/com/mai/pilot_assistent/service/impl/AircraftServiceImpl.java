package com.mai.pilot_assistent.service.impl;

import com.mai.pilot_assistent.model.Aircraft;
import com.mai.pilot_assistent.repository.AircraftRepository;
import com.mai.pilot_assistent.service.AircraftService;
import com.mai.pilot_assistent.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    @Autowired
    public AircraftServiceImpl(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<Aircraft> findById(String aircraftId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<List<Aircraft>> findByName(String name) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<Aircraft> createAircraft(Aircraft aircraft) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<Aircraft> updateAircraft(Aircraft aircraft) {
        return null;
    }
}
