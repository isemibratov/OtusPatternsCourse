package com.example.hw7.command.queue;

import com.example.hw7.command.Command;
import com.example.hw7.queue.LinkedListCommandQueue;

public class DeleteFirstElemenInQueueCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.pop();
    }
}
