package ru.silent_boy.spring.FirstRestApp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDTO {
    @NotNull(message = "Value must not be empty!")
    @Min(value = -100, message = "Value must not be less than -100!")
    @Max(value = 100, message = "Value must not be greater than 100!")
    private Double value;

    @NotNull(message = "Raining must not be empty!")
    private Boolean raining;

    @NotNull(message = "Sensor must not be empty!")
    private SensorDTO sensor;

    public MeasurementDTO() {}

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
