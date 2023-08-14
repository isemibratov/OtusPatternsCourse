package com.example.hw6.operations;

import java.util.Optional;

public interface Fuelable {
    Optional<Double> getFuel();

    void setFuel(double newValue);

    Optional<Double> getFuelConsumption();

    void setFuelConsumption(double newValue);
}
