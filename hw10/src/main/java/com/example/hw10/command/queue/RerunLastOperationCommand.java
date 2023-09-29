package com.example.hw10.command.queue;

import com.example.hw10.command.Command;
import com.example.hw10.queue.LinkedListCommandQueue;

public class RerunLastOperationCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.peek().execute();
    }
}
