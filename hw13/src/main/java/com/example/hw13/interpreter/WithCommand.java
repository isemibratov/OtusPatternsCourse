package com.example.hw13.interpreter;

import com.example.hw13.command.Command;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.ioc.IoC;
import com.example.hw13.operations.order.InterpretableOrder;

import java.util.Objects;

public class WithCommand implements Expression {
    private final String commandName;
    private final WithArgs withArgs;

    public WithCommand(String commandName, WithArgs withArgs) {
        this.commandName = commandName;
        this.withArgs = withArgs;
    }

    @Override
    public Command interpret(UObject interpretableOrder) {
        var interpretableOrderAdapter = IoC.<InterpretableOrder>resolve("InterpretableOrderAdapter", interpretableOrder);
        interpretableOrderAdapter.setCommandName(commandName);
        if (Objects.isNull(withArgs)) {
            return IoC.resolve(commandName);
        }
        return withArgs.interpret(interpretableOrder);
    }
}
