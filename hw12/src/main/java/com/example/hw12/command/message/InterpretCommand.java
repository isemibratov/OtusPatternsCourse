package com.example.hw12.command.message;

import com.example.hw12.command.Command;
import com.example.hw12.command.log.LogErrorCommand;
import com.example.hw12.command.operations.move.MoveLinearCommand;
import com.example.hw12.command.operations.move.MoveNonLinearCommand;
import com.example.hw12.command.queue.AddElementInQueueCommand;
import com.example.hw12.exception.exceptions.CommandException;
import com.example.hw12.game_objects.UObject;
import com.example.hw12.game_objects.UObjectWithId;
import com.example.hw12.game_objects.spaceship.DescribableByIdSpaceshipsObject;
import com.example.hw12.message.CommandMessageDto;
import com.example.hw12.queue.Games;

public class InterpretCommand implements Command {
    private final UObjectWithId objects;
    private final CommandMessageDto commandMessageDto;

    public InterpretCommand(CommandMessageDto commandMessageDto) {
        this.objects = DescribableByIdSpaceshipsObject.getInstance();
        this.commandMessageDto = commandMessageDto;
    }

    @Override
    public void execute() {
        var games = Games.getInstance();
        var gameQueue = games.getGameQueueById(commandMessageDto.getGameId());
        var object = (UObject) objects.getProperty(commandMessageDto.getObjId());

        var operationId = commandMessageDto.getOperationId();
        var command = getCommandForObjectByOperationIdAndArgs(object, operationId);

        new AddElementInQueueCommand(gameQueue, command).execute();
    }

    private Command getCommandForObjectByOperationIdAndArgs(
            UObject object,
            String operationId
    ) {
        Command command;
        switch (operationId) {
            case "MoveLinearCommand":
                command = new MoveLinearCommand(object);
                break;
            case "MoveNonLinearCommand":
                command = new MoveNonLinearCommand(object);
                break;
            default:
                command = new LogErrorCommand(this, new CommandException("Unknown operationId: "+operationId));
                break;
        }
        return command;
    }
}
