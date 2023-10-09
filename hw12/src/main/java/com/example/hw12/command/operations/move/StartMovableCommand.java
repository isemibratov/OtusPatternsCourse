package com.example.hw12.command.operations.move;

import com.example.hw12.command.Command;
import com.example.hw12.exception.ExceptionHandler;
import com.example.hw12.exception.exceptions.CommandException;
import com.example.hw12.ioc.IoC;
import com.example.hw12.game_objects.UObject;
import com.example.hw12.operations.Movable;

public class StartMovableCommand implements Command {
    private final Movable movableAdapter;

    public StartMovableCommand(UObject movableObject) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
    }

    @Override
    public void execute() {
        try {
            ((Command) movableAdapter.getMovement()
                    .orElseThrow(() -> new CommandException("Error when start movable")))
                    .execute();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
