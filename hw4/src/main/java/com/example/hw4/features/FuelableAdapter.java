package com.example.hw4.features;

import com.example.hw4.objects.UObject;

import java.util.Optional;

public class FuelableAdapter implements Fuelable{
    private final UObject fuelableObject;

    public FuelableAdapter(UObject fuelableObject) {
        this.fuelableObject = fuelableObject;
    }

    @Override
    public Optional<Double> getRemainingFuel() {
        return Optional.ofNullable((Double) fuelableObject.getProperty("Fuel"));
    }

    @Override
    public Optional<Double> getFuelConsumption() {
        return Optional.ofNullable((Double) fuelableObject.getProperty("FuelConsumption"));
    }

    @Override
    public void setRemainingFuel(double newValue) {
        fuelableObject.setProperty("Fuel", newValue);
    }

    @Override
    public void setFuelConsumption(double newValue) {
        fuelableObject.setProperty("FuelConsumption", newValue);
    }
}
