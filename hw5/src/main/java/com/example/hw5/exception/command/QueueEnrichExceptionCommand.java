package com.example.hw5.exception.command;

import com.example.hw5.command.Command;
import com.example.hw5.command.queue.AddElementInQueueCommand;

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
