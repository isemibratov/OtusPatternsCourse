package com.example.hw5.command.queue;

import com.example.hw5.command.Command;
import com.example.hw5.queue.LinkedListCommandQueue;

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
