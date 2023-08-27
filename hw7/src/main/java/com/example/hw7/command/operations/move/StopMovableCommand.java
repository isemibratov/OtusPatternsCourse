package com.example.hw7.command.operations.move;

import com.example.hw7.command.Command;
import com.example.hw7.command.EmptyCommand;
import com.example.hw7.exception.ExceptionHandler;
import com.example.hw7.exception.exceptions.CommandException;
import com.example.hw7.operations.Movable;

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
