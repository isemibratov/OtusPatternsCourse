package com.example.hw13.state;


public class NormalState implements State {
    public NormalState() {
    }

    @Override
    public State handle() {
        return this;
    }
}
