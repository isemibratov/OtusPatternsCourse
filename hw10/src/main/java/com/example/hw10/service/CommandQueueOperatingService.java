package com.example.hw10.service;

import com.example.hw10.command.Command;
import com.example.hw10.command.queue.DeleteFirstElemenInQueueCommand;
import com.example.hw10.exception.ExceptionHandler;
import com.example.hw10.queue.LinkedListCommandQueue;

public class CommandQueueOperatingService {
    private final LinkedListCommandQueue queue;

    public CommandQueueOperatingService() {
        this.queue = LinkedListCommandQueue.getInstance();
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
