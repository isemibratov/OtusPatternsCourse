package com.example.hw6.service;

import com.example.hw6.command.Command;
import com.example.hw6.command.queue.AddElementsListInQueueCommand;
import com.example.hw6.command.queue.DeleteFirstElemenInQueueCommand;
import com.example.hw6.exception.ExceptionHandler;
import com.example.hw6.queue.LinkedListCommandQueue;

import java.util.List;

public class CommandQueueOperatingService {
    private final LinkedListCommandQueue queue;

    public CommandQueueOperatingService() {
        this.queue = LinkedListCommandQueue.getInstance();
    }

    public void updateQueue(List<Command> elements) {
        new AddElementsListInQueueCommand(elements).execute();
    }

    public void process() {
        while (queue.size() > 0) {
            Command cmd = queue.peek();
            try {
                cmd.execute();
            } catch (Exception e) {
                ExceptionHandler
                        .handle(e, cmd)
                        .execute();
            } finally {
                 new DeleteFirstElemenInQueueCommand().execute();
            }
        }
    }
}
