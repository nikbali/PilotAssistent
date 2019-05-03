package com.mai.pilot_assistent.service.impl;

import com.google.common.base.Preconditions;
import com.mai.pilot_assistent.model.Aircraft;
import com.mai.pilot_assistent.repository.AircraftRepository;
import com.mai.pilot_assistent.service.AircraftService;
import com.mai.pilot_assistent.service.ImageCloudService;
import com.mai.pilot_assistent.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final ImageCloudService imageCloudService;

    @Autowired
    public AircraftServiceImpl(AircraftRepository aircraftRepository, ImageCloudService imageCloudService) {
        this.aircraftRepository = aircraftRepository;
        this.imageCloudService = imageCloudService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<Aircraft> findById(String aircraftId) {
        return Result.retrieve(
                () -> {
                    Preconditions.checkArgument(aircraftId != null);
                    return aircraftRepository.findById(aircraftId)
                            .orElseThrow(() -> new RuntimeException(String.format("Самолет с Id: %s не найден", aircraftId)));
                });
    }

    @Override
    public Result<List<Aircraft>> findAll() {
        return Result.retrieve(aircraftRepository::findAll);
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
    public Result<Aircraft> createAircraft(MultipartFile image, Aircraft aircraft) {
        return Result.retrieve(
                () -> {
                    Preconditions.checkArgument(aircraft != null);
                    if (image != null) {
                        String urlImage = imageCloudService.upload(image).getValue();
                        aircraft.setImageUrl(urlImage);
                    }
                    return aircraftRepository.save(aircraft);
                }
        );
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Result<Aircraft> updateAircraft(Aircraft aircraft) {
        return null;
    }
}
