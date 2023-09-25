package com.example.hw10.state;


public class NormalState implements State {
    public NormalState() {
    }

    @Override
    public State handle() {
        return this;
    }
}
