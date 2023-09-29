package com.example.hw10.command.state;

import com.example.hw10.command.Command;
import com.example.hw10.objects.ContextObject;
import com.example.hw10.objects.UObject;
import com.example.hw10.state.NormalState;

public class RunCommand implements Command {
    private final UObject context;

    public RunCommand() {
        this.context = ContextObject.getInstance();
    }


    @Override
    public void execute() {
        context.setProperty(
                "state",
                new NormalState());
    }
}
