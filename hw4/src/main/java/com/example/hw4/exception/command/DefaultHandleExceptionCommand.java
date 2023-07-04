package com.example.hw4.exception.command;

import com.example.hw4.command.Command;
import com.example.hw4.command.log.LogErrorCommand;

public class DefaultHandleExceptionCommand implements ExceptionCommand {
    private final Command command;
    private final Exception exception;

    public DefaultHandleExceptionCommand(Command command, Exception exception) {
        this.command = command;
        this.exception = exception;
    }
    @Override
    public void execute() {
        new LogErrorCommand(command, exception).execute();
    }
}
