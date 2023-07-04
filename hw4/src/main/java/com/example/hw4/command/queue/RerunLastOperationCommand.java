package com.example.hw4.command.queue;

import com.example.hw4.command.Command;
import com.example.hw4.queue.LinkedListCommandQueue;

public class RerunLastOperationCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.peek().execute();
    }
}
