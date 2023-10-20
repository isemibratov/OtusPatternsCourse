package com.example.hw13.command.thread;

import com.example.hw13.command.Command;
import com.example.hw13.game_objects.context.ContextObject;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.queue.LinkedListCommandQueue;
import com.example.hw13.state.NormalState;
import com.example.hw13.state.State;

import java.util.Optional;

public class InitCommand implements Command {
    private final UObject context;
    private final State state;

    public InitCommand() {
        this.context = ContextObject.getInstance();
        this.state = new NormalState();
    }

    @Override
    public void execute() {
        var queue = new LinkedListCommandQueue();
        context.setProperty("state", state);
        context.setProperty("queue", queue);
        context.setProperty("process", (Runnable) () -> {
            var command = queue.pop();
            Optional
                    .ofNullable(command)
                    .ifPresent(Command::execute);
        });
    }
}
