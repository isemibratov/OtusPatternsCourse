package com.example.hw4.exception.command;

import com.example.hw4.command.Command;
import com.example.hw4.command.log.LogQueueSizeCommand;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public abstract class ExceptionHandlerCommandFactory {
    private static Table<String, String, Runnable> commands;
    private static Command commandVal;
    private static Exception exceptionVal;
    private static ExceptionCommand exceptionCommand;

    private ExceptionHandlerCommandFactory() {
        throw new IllegalStateException("Exception handler command class");
    }

    public static ExceptionCommand getInstance(Command command, Exception exception) {
        setCommandVal(command);
        setExceptionVal(exception);
        if (commands == null) {initialize();}
        commands
                .row(command.getClass().getName())
                .getOrDefault(
                        exception.getClass().getName(),
                        () -> exceptionCommand = new DefaultHandleExceptionCommand(command, exception)
                ).run();

        return exceptionCommand;
    }

    public static void setCommandVal(Command commandVal) {
        ExceptionHandlerCommandFactory.commandVal = commandVal;
    }

    public static void setExceptionVal(Exception exceptionVal) {
        ExceptionHandlerCommandFactory.exceptionVal = exceptionVal;
    }

    private static void initialize() {
        commands = HashBasedTable.create();
        commands.put(
                LogQueueSizeCommand.class.getName(),
                RuntimeException.class.getName(),
                () -> setExceptionCommand(new LogErrorExceptionCommand(commandVal, exceptionVal)));
        commands.put(
                LogQueueSizeCommand.class.getName(),
                IllegalStateException.class.getName(),
                () -> setExceptionCommand(new QueueEnrichExceptionCommand(commandVal)));
    }

    public static void setExceptionCommand(ExceptionCommand exceptionCommand) {
        ExceptionHandlerCommandFactory.exceptionCommand = exceptionCommand;
    }
}
