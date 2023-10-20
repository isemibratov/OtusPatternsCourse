package com.example.hw13.state;

import com.example.hw13.game_objects.context.ContextObject;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.queue.LinkedListCommandQueue;

public class MoveToState implements State {
    private final UObject context;

    public MoveToState() {this.context = ContextObject.getInstance();}

    @Override
    public State handle() {
        var newQueue = new LinkedListCommandQueue();
        var queue = ((LinkedListCommandQueue) context.getProperty("queue"));
        while (queue.size() > 0) {
            newQueue.push(queue.pop());
        }
        context.setProperty("newQueue", newQueue);

        return (State) context.getProperty("state");
    }
}
