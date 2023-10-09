package com.example.hw12.command.queue;

import com.example.hw12.command.Command;
import com.example.hw12.queue.LinkedListCommandQueue;

public class RerunLastOperationCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.peek().execute();
    }
}
