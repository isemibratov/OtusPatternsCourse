package com.example.hw10.command.thread;

import com.example.hw10.command.Command;
import com.example.hw10.objects.ContextObject;
import com.example.hw10.objects.UObject;
import com.example.hw10.queue.LinkedListCommandQueue;

public class SoftStopCommand implements Command {
    private final UObject context;

    public SoftStopCommand() {
        this.context = ContextObject.getInstance();
    }

    @Override
    public void execute() {
        Runnable previousProcess = (Runnable)context.getProperty("process");
        context.setProperty(
                "process",
                (Runnable) () -> {
                    previousProcess.run();

                    var queue = (LinkedListCommandQueue)context.getProperty("queue");

                    if (0 == queue.size()){
                        context.setProperty("state", null);
                    }
                }
        );
    }
}
