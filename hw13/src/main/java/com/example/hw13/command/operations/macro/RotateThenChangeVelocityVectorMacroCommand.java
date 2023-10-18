package com.example.hw13.command.operations.macro;

import com.example.hw13.command.Command;
import com.example.hw13.command.MacroCommand;
import com.example.hw13.command.operations.ChangeVelocityVectorCommand;
import com.example.hw13.command.operations.RotateCommand;
import com.example.hw13.exception.exceptions.CommandException;
import com.example.hw13.game_objects.UObject;

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
