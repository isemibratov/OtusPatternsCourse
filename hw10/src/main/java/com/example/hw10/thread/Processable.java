package com.example.hw10.thread;

import com.example.hw10.state.State;

import java.util.Optional;

public interface Processable {
    Optional<State> getState();
    
    void process();
}
