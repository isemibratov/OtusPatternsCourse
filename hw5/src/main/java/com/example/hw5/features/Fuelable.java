package com.example.hw5.features;

import java.util.Optional;

public interface Fuelable {
    Optional<Double> getRemainingFuel();
    Optional<Double> getFuelConsumption();
    void setRemainingFuel(double newValue);
    void setFuelConsumption(double newValue);
}
