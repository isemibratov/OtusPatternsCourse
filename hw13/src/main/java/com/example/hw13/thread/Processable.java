package com.example.hw13.thread;

import com.example.hw13.state.State;

import java.util.Optional;

public interface Processable {
    Optional<State> getState();
    
    void process();
}
