package com.example.hw5.exception;

import com.example.hw5.command.Command;
import com.example.hw5.exception.command.ExceptionCommand;
import com.example.hw5.exception.command.ExceptionHandlerCommandFactory;

public class ExceptionHandler {
    private ExceptionHandler() {
        throw new IllegalStateException("Exception handler class");
    }

    public static ExceptionCommand handle(Exception exception, Command command) {
        return ExceptionHandlerCommandFactory.getInstance(command, exception);
    }
}
