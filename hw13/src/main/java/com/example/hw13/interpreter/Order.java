package com.example.hw13.interpreter;

import com.example.hw13.command.Command;
import com.example.hw13.game_objects.UObject;

public class Order implements Expression {
    private final ForSubject forSubject;

    public Order(ForSubject forSubject) {
        this.forSubject = forSubject;
    }

    @Override
    public Command interpret(UObject interpretableOrder) {
        return forSubject.interpret(interpretableOrder);
    }
}
