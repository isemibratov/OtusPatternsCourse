package com.example.hw12.thread;

import com.example.hw12.state.State;

import java.util.Optional;

public interface Processable {
    Optional<State> getState();
    
    void process();
}
