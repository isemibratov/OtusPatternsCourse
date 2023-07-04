package com.example.hw3.exception.command;

import com.example.hw3.command.Command;
import com.example.hw3.command.log.LogErrorCommand;

public class LogErrorExceptionCommand implements ExceptionCommand {
    private final Command command;
    private final Exception exception;

    public LogErrorExceptionCommand(Command command, Exception exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        new LogErrorCommand(command, exception).execute();
    }
}
