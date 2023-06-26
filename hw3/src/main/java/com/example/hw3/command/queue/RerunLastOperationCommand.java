package com.example.hw3.command.queue;

import com.example.hw3.command.Command;
import com.example.hw3.queue.LinkedListCommandQueue;

public class RerunLastOperationCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.peek().execute();
    }
}
