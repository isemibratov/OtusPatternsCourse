package com.example.hw13.command.message;

import com.example.hw13.command.Command;
import com.example.hw13.exception.ExceptionHandler;
import com.example.hw13.message.CommandMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProcessMessageCommand implements Command {
    private final String commandMessage;
    private ObjectMapper objectMapper;

    public ProcessMessageCommand(String commandMessage) {
        this.commandMessage = commandMessage;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void execute() {
        try {
            var commandMessageDto = objectMapper.readValue(commandMessage, CommandMessageDto.class);
            new InterpretCommand(commandMessageDto).execute();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
