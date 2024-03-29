package com.example.hw11.command.operations.move;

import com.example.hw11.command.Command;
import com.example.hw11.command.EmptyCommand;
import com.example.hw11.exception.ExceptionHandler;
import com.example.hw11.exception.exceptions.CommandException;
import com.example.hw11.operations.Movable;

public class StopMovableCommand implements Command {
    private final Movable movableAdapter;

    public StopMovableCommand(Movable movableAdapter) {
        this.movableAdapter = movableAdapter;
    }

    @Override
    public void execute() {
        try {
            movableAdapter.getMovement()
                    .orElseThrow(() -> new CommandException("Error when finish movable"))
                    .inject(new EmptyCommand());
            movableAdapter.setMovement(null);
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
