package com.example.hw12.thread;

import com.example.hw12.game_objects.context.ContextObject;
import com.example.hw12.game_objects.UObject;
import com.example.hw12.state.State;

import java.util.Optional;

public class ProcessableImpl implements Processable{
    private final UObject context;

    public ProcessableImpl() {
        this.context = ContextObject.getInstance();
    }

    @Override
    public Optional<State> getState() {
        return Optional.ofNullable((State) context.getProperty("state"));
    }

    @Override
    public void process() {
         getState().ifPresent(State::handle);
         ((Runnable) context.getProperty("process")).run();
    }
}
