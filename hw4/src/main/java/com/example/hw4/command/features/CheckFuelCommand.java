package com.example.hw4.command.features;

import com.example.hw4.command.Command;
import com.example.hw4.exception.exceptions.CheckFuelCommandException;
import com.example.hw4.exception.exceptions.CommandException;
import com.example.hw4.features.FuelableAdapter;
import com.example.hw4.objects.UObject;

public class CheckFuelCommand implements Command {
    private final FuelableAdapter fuelableAdapter;

    public CheckFuelCommand(UObject fuelableObject) {
        this.fuelableAdapter = new FuelableAdapter(fuelableObject);
    }

    @Override
    public void execute() {
        try {
            double fuel = fuelableAdapter.getRemainingFuel()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel found"));
            double fuelConsumption = fuelableAdapter.getFuelConsumption()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel consumption found"));
            if (fuel < fuelConsumption) {
                throw new CheckFuelCommandException("not enough fuel");
            }
        } catch (Exception ex) {
            throw new CommandException("Error when try to check fuel. verdict: " + ex.getMessage());
        }
    }
}
