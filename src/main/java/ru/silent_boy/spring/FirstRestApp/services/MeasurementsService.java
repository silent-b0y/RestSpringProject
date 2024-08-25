package ru.silent_boy.spring.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.silent_boy.spring.FirstRestApp.models.Measurement;
import ru.silent_boy.spring.FirstRestApp.repositories.MeasurementsRepository;
import ru.silent_boy.spring.FirstRestApp.repositories.SensorsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsRepository sensorsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsRepository sensorsRepository) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsRepository = sensorsRepository;
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public long countRainyDays() {
        return measurementsRepository.countByRainingTrue();
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
        measurement.setSensor(sensorsRepository.findByName(measurement.getSensor().getName()).get());
    }
}
