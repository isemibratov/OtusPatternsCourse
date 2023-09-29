package com.example.hw10.command.operations;

import com.example.hw10.command.Command;
import com.example.hw10.command.operations.move.StopMovableCommand;
import com.example.hw10.operations.Movable;

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
