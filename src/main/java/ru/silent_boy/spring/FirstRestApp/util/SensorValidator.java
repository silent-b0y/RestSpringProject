package ru.silent_boy.spring.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.silent_boy.spring.FirstRestApp.models.Sensor;
import ru.silent_boy.spring.FirstRestApp.repositories.SensorsRepository;

@Component
public class SensorValidator implements Validator {
    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorValidator(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (sensorsRepository.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "Sensor already exists");
        }
    }
}
