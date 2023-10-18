package com.example.hw13.command.order;

import com.example.hw13.command.Command;
import com.example.hw13.command.MacroCommand;
import com.example.hw13.exception.exceptions.CommandException;
import com.example.hw13.game_objects.UObject;

public class OrderMacroCommand implements Command {
    private final Command[] commands;

    public OrderMacroCommand(UObject order, UObject subject) {
        this.commands = new Command[] {
                new CheckOrderCommand(order, subject),
                new InterpretOrderCommand(order, subject)
        };
    }

    @Override
    public void execute() {
        try {
            new MacroCommand(commands).execute();
        } catch (Exception ex) {
            throw new CommandException("error when process order");
        }
    }
}
