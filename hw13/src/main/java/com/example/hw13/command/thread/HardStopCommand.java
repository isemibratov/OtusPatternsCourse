package com.example.hw13.command.thread;

import com.example.hw13.command.Command;
import com.example.hw13.game_objects.context.ContextObject;
import com.example.hw13.game_objects.UObject;

public class HardStopCommand implements Command {
    private final UObject context;

    public HardStopCommand() {
        this.context = ContextObject.getInstance();
    }

    @Override
    public void execute() {
        context.setProperty("state", null);
    }
}
