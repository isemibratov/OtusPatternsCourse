package com.example.hw12.command.queue;

import com.example.hw12.command.Command;
import com.example.hw12.queue.LinkedListCommandQueue;

import java.util.List;

public class AddElementsListInQueueCommand implements Command {
    private final LinkedListCommandQueue queue;
    private final List<Command> elements;

    public AddElementsListInQueueCommand(List<Command> elements) {
        this.queue = LinkedListCommandQueue.getInstance();
        this.elements = elements;
    }

    public AddElementsListInQueueCommand(
            LinkedListCommandQueue queue,
            List<Command> elements
    ) {
        this.queue = queue;
        this.elements = elements;
    }

    @Override
    public void execute() {
        elements
                .forEach(queue::push);
    }
}
