package com.example.hw4.command.features.macro;

import com.example.hw4.command.Command;
import com.example.hw4.command.features.BurnFuelCommand;
import com.example.hw4.command.features.CheckFuelCommand;
import com.example.hw4.command.features.LinearMoveCommand;
import com.example.hw4.exception.exceptions.CommandException;
import com.example.hw4.objects.UObject;

import java.util.Arrays;

public class LinearMoveWithFuelConsumptionMacroCommand implements Command {
    private final Command[] commands;

    public LinearMoveWithFuelConsumptionMacroCommand(UObject object) {
        this.commands = new Command[] {
                new CheckFuelCommand(object),
                new LinearMoveCommand(object),
                new BurnFuelCommand(object)
        };
    }

    @Override
    public void execute() {
        try {
            Arrays.stream(commands).iterator()
                    .forEachRemaining(Command::execute);
        } catch (Exception ex) {
            throw new CommandException("error when move with fuel consumption");
        }
    }
}

