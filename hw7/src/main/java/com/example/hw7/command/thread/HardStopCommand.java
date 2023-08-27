package com.example.hw7.command.thread;

import com.example.hw7.command.Command;
import com.example.hw7.objects.ContextObject;
import com.example.hw7.objects.UObject;

public class HardStopCommand implements Command {
    private final UObject context;

    public HardStopCommand() {
        this.context = ContextObject.getInstance();
    }

    @Override
    public void execute() {
        context.setProperty("canContinue", false);
    }
}
