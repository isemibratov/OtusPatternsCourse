package com.example.hw13.command;

import com.example.hw13.exception.exceptions.CommandException;

import java.util.Arrays;

public class MacroCommand implements Command {
    private final Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        try {
            Arrays.stream(commands).iterator()
                    .forEachRemaining(Command::execute);
        } catch (Exception ex) {
            throw new CommandException("error when perform macro command");
        }
    }
}
