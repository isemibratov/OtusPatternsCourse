package com.example.hw7.command.operations.macro;

import com.example.hw7.command.Command;
import com.example.hw7.command.MacroCommand;
import com.example.hw7.command.operations.BurnFuelCommand;
import com.example.hw7.command.operations.CheckFuelCommand;
import com.example.hw7.command.operations.move.MoveLinearCommand;
import com.example.hw7.exception.exceptions.CommandException;
import com.example.hw7.objects.UObject;

public class LinearMoveWithFuelConsumptionMacroCommand implements Command {
    private final Command[] commands;

    public LinearMoveWithFuelConsumptionMacroCommand(UObject object) {
        this.commands = new Command[] {
                new CheckFuelCommand(object),
                new MoveLinearCommand(object),
                new BurnFuelCommand(object)
        };
    }

    @Override
    public void execute() {
        try {
            new MacroCommand(commands).execute();
        } catch (Exception ex) {
            throw new CommandException("error when move with fuel consumption");
        }
    }
}

