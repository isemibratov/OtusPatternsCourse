package com.example.hw4.exception.command;

import com.example.hw4.command.Command;
import com.example.hw4.command.log.LogErrorCommand;
import com.example.hw4.command.queue.RerunLastOperationCommand;

public class TryTwiceOrLogExceptionCommand implements ExceptionCommand {
    private final Command command;
    private Exception exception;
    private int errorsCounter;

    public TryTwiceOrLogExceptionCommand(Command command, Exception exception) {
        this.command = command;
        this.exception = exception;
        this.errorsCounter = 0;
    }

    @Override
    public void execute() {
        while (errorsCounter < 2) {
            try {
                new RerunLastOperationCommand().execute();
                break;
            } catch (Exception ex) {
                exception = ex;
                errorsCounter += 1;
            }
        }
        if (errorsCounter == 2){
            new LogErrorCommand(command, exception).execute();
        }
    }
}
