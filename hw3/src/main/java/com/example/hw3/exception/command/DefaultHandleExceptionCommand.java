package com.example.hw3.exception.command;

import com.example.hw3.command.Command;
import com.example.hw3.command.log.LogErrorCommand;

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
