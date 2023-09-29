package com.example.hw11.command.exception;

import com.example.hw11.command.Command;
import com.example.hw11.command.log.LogErrorCommand;

public class DefaultHandleExceptionCommand implements Command {
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
