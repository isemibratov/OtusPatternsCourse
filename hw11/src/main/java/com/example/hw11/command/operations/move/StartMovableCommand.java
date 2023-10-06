package com.example.hw11.command.operations.move;

import com.example.hw11.command.Command;
import com.example.hw11.exception.ExceptionHandler;
import com.example.hw11.exception.exceptions.CommandException;
import com.example.hw11.ioc.IoC;
import com.example.hw11.game_objects.UObject;
import com.example.hw11.operations.Movable;

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
