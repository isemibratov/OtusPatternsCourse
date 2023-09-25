package com.example.hw10.command.operations;

import com.example.hw10.command.Command;
import com.example.hw10.exception.exceptions.CheckFuelCommandException;
import com.example.hw10.exception.exceptions.CommandException;
import com.example.hw10.ioc.IoC;
import com.example.hw10.operations.Fuelable;
import com.example.hw10.objects.UObject;

public class BurnFuelCommand implements Command {
    private final Fuelable fuelableAdapter;

    public BurnFuelCommand(UObject fuelableObject) {
        this.fuelableAdapter = IoC.resolve("FuelableAdapter", fuelableObject);
    }

    @Override
    public void execute() {
        try {
            double fuel = fuelableAdapter.getFuel()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel found"));
            double fuelConsumption = fuelableAdapter.getFuelConsumption()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel consumption found"));
            fuelableAdapter.setFuel(fuel - fuelConsumption);
        } catch (Exception ex) {
            throw new CommandException("Error when try to burn fuel. verdict: " + ex.getMessage());
        }
    }
}
