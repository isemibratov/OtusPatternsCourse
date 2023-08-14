package com.example.hw7.command.exception;

import com.example.hw7.command.Command;
import com.example.hw7.command.log.LogErrorCommand;

public class LogErrorExceptionCommand implements Command {
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
