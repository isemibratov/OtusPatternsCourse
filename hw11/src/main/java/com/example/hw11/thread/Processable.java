package com.example.hw11.thread;

import com.example.hw11.state.State;

import java.util.Optional;

public interface Processable {
    Optional<State> getState();
    
    void process();
}
