package com.example.hw11.command.queue;

import com.example.hw11.command.Command;
import com.example.hw11.queue.LinkedListCommandQueue;

public class AddElementInQueueCommand implements Command {
    private final Command element;

    public AddElementInQueueCommand(Command element) {
        this.element = element;
    }

    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.push(element);
    }
}
