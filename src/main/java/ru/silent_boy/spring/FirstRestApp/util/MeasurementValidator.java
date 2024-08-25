package ru.silent_boy.spring.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.silent_boy.spring.FirstRestApp.models.Measurement;
import ru.silent_boy.spring.FirstRestApp.repositories.SensorsRepository;

@Component
public class MeasurementValidator implements Validator {
    private final SensorsRepository sensorsRepository;

    @Autowired
    public MeasurementValidator(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (sensorsRepository.findByName(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "Sensor doesn't exists");
        }
    }
}
