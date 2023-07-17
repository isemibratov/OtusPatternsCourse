package com.example.hw5.command.queue;

import com.example.hw5.command.Command;
import com.example.hw5.queue.LinkedListCommandQueue;

public class DeleteFirstElemenInQueueCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.pop();
    }
}
