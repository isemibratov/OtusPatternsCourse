package com.example.hw13.command.operations;

import com.example.hw13.command.Command;
import com.example.hw13.command.operations.move.StopMovableCommand;
import com.example.hw13.operations.Movable;

public class StopCommand implements Command {
    private final Command command;

    public StopCommand(Movable movableAdapter) {
        this.command = new StopMovableCommand(movableAdapter);
    }

    @Override
    public void execute() {
        command.execute();
    }
}
