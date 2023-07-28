package com.example.hw5.command.features;

import com.example.hw5.command.Command;
import com.example.hw5.exception.exceptions.CheckFuelCommandException;
import com.example.hw5.exception.exceptions.CommandException;
import com.example.hw5.features.FuelableAdapter;
import com.example.hw5.objects.UObject;

public class BurnFuelCommand implements Command {
    private final FuelableAdapter fuelableAdapter;

    public BurnFuelCommand(UObject fuelableObject) {
        this.fuelableAdapter = new FuelableAdapter(fuelableObject);
    }

    @Override
    public void execute() {
        try {
            double fuel = fuelableAdapter.getRemainingFuel()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel found"));
            double fuelConsumption = fuelableAdapter.getFuelConsumption()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel consumption found"));
            fuelableAdapter.setRemainingFuel(fuel-fuelConsumption);
        } catch (Exception ex) {
            throw new CommandException("Error when try to burn fuel. verdict: " + ex.getMessage());
        }
    }
}
