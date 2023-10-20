package com.example.hw13.command.exception;

import com.example.hw13.command.Command;
import com.example.hw13.exception.exceptions.CommandException;

public class ThrowExceptionCommand implements Command {
    private final String commandName;
    private final Exception exception;

    public ThrowExceptionCommand(String commandName, Exception exception) {
        this.commandName = commandName;
        this.exception = exception;
    }

    @Override
    public void execute() {
        var message = String.format(
                "Error when run %s - verdict: %s",
                commandName,
                exception.getMessage());
        throw new CommandException(message);
    }
}
