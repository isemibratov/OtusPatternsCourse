package com.example.hw10.command.thread;

import com.example.hw10.command.Command;
import com.example.hw10.objects.ContextObject;
import com.example.hw10.objects.UObject;

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
