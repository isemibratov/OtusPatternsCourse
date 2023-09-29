package com.example.hw11.command.thread;

import com.example.hw11.command.Command;
import com.example.hw11.game_objects.context.ContextObject;
import com.example.hw11.game_objects.UObject;

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
