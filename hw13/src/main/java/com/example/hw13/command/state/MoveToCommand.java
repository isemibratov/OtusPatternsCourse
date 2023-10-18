package com.example.hw13.command.state;

import com.example.hw13.command.Command;
import com.example.hw13.game_objects.context.ContextObject;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.state.MoveToState;

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
