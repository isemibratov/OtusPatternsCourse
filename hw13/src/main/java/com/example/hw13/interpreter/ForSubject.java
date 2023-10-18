package com.example.hw13.interpreter;

import com.example.hw13.command.Command;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.ioc.IoC;
import com.example.hw13.operations.order.InterpretableOrder;

import java.util.Objects;

public class ForSubject implements Expression {
    private final UObject subject;
    private final WithCommand withCommand;

    public ForSubject(UObject subject, WithCommand withCommand) {
        this.subject = subject;
        this.withCommand = withCommand;
    }

    @Override
    public Command interpret(UObject interpretableOrder) {
        var interpretableOrderAdapter = IoC.<InterpretableOrder>resolve("InterpretableOrderAdapter", interpretableOrder);
        interpretableOrderAdapter.setSubject(subject);
        if ( Objects.isNull(withCommand) ) {
            return IoC.resolve("EmptyCommand");
        }
        return withCommand.interpret(interpretableOrder);
    }
}
