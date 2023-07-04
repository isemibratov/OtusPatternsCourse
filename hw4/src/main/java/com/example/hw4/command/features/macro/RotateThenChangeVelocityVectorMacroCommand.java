package com.example.hw4.command.features.macro;

import com.example.hw4.command.Command;
import com.example.hw4.command.features.ChangeVelocityCommand;
import com.example.hw4.command.features.RotateCommand;
import com.example.hw4.exception.exceptions.CommandException;
import com.example.hw4.objects.UObject;

import java.util.Arrays;

public class RotateThenChangeVelocityVectorMacroCommand implements Command {
    private final Command[] commands;

    public RotateThenChangeVelocityVectorMacroCommand(UObject rotatableObject) {
        this.commands = new Command[] {
                new RotateCommand(rotatableObject),
                new ChangeVelocityCommand(rotatableObject)
        };
    }

    @Override
    public void execute() {
        try {
            Arrays.stream(commands).iterator()
                    .forEachRemaining(Command::execute);
        } catch (Exception ex) {
            throw new CommandException("error when try to rotate with changing velocity vector");
        }
    }
}
