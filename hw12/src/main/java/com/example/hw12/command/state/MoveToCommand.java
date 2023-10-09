package com.example.hw12.command.state;

import com.example.hw12.command.Command;
import com.example.hw12.game_objects.context.ContextObject;
import com.example.hw12.game_objects.UObject;
import com.example.hw12.state.MoveToState;

public class MoveToCommand implements Command {
    private final UObject context;

    public MoveToCommand() {
        this.context = ContextObject.getInstance();
    }


    @Override
    public void execute() {
        context.setProperty(
                "state",
                new MoveToState());
    }
}
