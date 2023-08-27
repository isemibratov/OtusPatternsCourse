package com.example.hw7.command.thread;

import com.example.hw7.command.Command;
import com.example.hw7.objects.ContextObject;
import com.example.hw7.objects.UObject;
import com.example.hw7.queue.LinkedListCommandQueue;

public class InitCommand implements Command {
    private final UObject context;

    public InitCommand() {
        this.context = ContextObject.getInstance();
    }

    @Override
    public void execute() {
        var queue = new LinkedListCommandQueue();
        context.setProperty("canContinue", true);
        context.setProperty("queue", queue);
        context.setProperty("process", (Runnable) () -> {
            var command = queue.pop();

            command.execute();
        });
    }
}
