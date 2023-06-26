package com.example.hw3.exception.command;

import com.example.hw3.command.Command;
import com.example.hw3.command.queue.AddElementInQueueCommand;

public class QueueEnrichExceptionCommand implements ExceptionCommand {
    private final Command command;

    public QueueEnrichExceptionCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        new AddElementInQueueCommand(command).execute();
    }
}
