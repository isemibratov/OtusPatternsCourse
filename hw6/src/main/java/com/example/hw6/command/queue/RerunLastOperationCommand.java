package com.example.hw6.command.queue;

import com.example.hw6.command.Command;
import com.example.hw6.queue.LinkedListCommandQueue;

public class RerunLastOperationCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.peek().execute();
    }
}
