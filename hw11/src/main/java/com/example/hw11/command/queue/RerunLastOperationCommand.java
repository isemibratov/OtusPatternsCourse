package com.example.hw11.command.queue;

import com.example.hw11.command.Command;
import com.example.hw11.queue.LinkedListCommandQueue;

public class RerunLastOperationCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.peek().execute();
    }
}
