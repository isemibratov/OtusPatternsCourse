package com.example.hw13.command.queue;

import com.example.hw13.command.Command;
import com.example.hw13.queue.LinkedListCommandQueue;

public class RerunLastOperationCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.peek().execute();
    }
}
