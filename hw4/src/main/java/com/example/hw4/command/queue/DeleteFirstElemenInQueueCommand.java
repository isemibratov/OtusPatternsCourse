package com.example.hw4.command.queue;

import com.example.hw4.command.Command;
import com.example.hw4.queue.LinkedListCommandQueue;

public class DeleteFirstElemenInQueueCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.pop();
    }
}
