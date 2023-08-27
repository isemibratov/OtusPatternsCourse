package com.example.hw7.exception;

import com.example.hw7.command.Command;

public class ExceptionHandler {
    private ExceptionHandler() {
        throw new IllegalStateException("Exception handler class");
    }

    public static Command handle(Exception exception, Command command) {
        return ExceptionHandlerCommandFactory.getInstance(command, exception);
    }
}
