package com.example.hw10.command.operations.macro;

import com.example.hw10.command.Command;
import com.example.hw10.command.MacroCommand;
import com.example.hw10.command.operations.ChangeVelocityVectorCommand;
import com.example.hw10.command.operations.RotateCommand;
import com.example.hw10.exception.exceptions.CommandException;
import com.example.hw10.objects.UObject;

public class RotateThenChangeVelocityVectorMacroCommand implements Command {
    private final Command[] commands;

    public RotateThenChangeVelocityVectorMacroCommand(UObject rotatableObject) {
        this.commands = new Command[] {
                new RotateCommand(rotatableObject),
                new ChangeVelocityVectorCommand(rotatableObject)
        };
    }

    @Override
    public void execute() {
        try {
            new MacroCommand(commands).execute();
        } catch (Exception ex) {
            throw new CommandException("error when try to rotate with changing velocity vector");
        }
    }
}
