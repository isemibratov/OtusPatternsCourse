package com.example.hw13.command.queue;

import com.example.hw13.command.Command;
import com.example.hw13.queue.LinkedListCommandQueue;

public class DeleteFirstElemenInQueueCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.pop();
    }
}
