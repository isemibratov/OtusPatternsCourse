package com.example.hw12.command.queue;

import com.example.hw12.command.Command;
import com.example.hw12.queue.LinkedListCommandQueue;

public class AddElementInQueueCommand implements Command {
    private final LinkedListCommandQueue queue;
    private final Command element;

    public AddElementInQueueCommand(Command element) {
        this.queue = LinkedListCommandQueue.getInstance();
        this.element = element;
    }

    public AddElementInQueueCommand(
            LinkedListCommandQueue queue,
            Command element
    ) {
        this.queue = queue;
        this.element = element;
    }

    @Override
    public void execute() {
        queue.push(element);
    }
}
