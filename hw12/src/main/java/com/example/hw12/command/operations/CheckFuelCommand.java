package com.example.hw12.command.operations;

import com.example.hw12.command.Command;
import com.example.hw12.exception.exceptions.CheckFuelCommandException;
import com.example.hw12.exception.exceptions.CommandException;
import com.example.hw12.ioc.IoC;
import com.example.hw12.game_objects.UObject;
import com.example.hw12.operations.Fuelable;

public class CheckFuelCommand implements Command {
    private final Fuelable fuelableAdapter;

    public CheckFuelCommand(UObject fuelableObject) {
        this.fuelableAdapter = IoC.resolve("FuelableAdapter", fuelableObject);
    }

    @Override
    public void execute() {
        try {
            double fuel = fuelableAdapter.getFuel()
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
