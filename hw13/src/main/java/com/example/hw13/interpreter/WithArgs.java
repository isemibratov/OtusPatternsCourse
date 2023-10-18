package com.example.hw13.interpreter;

import com.example.hw13.command.Command;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.ioc.IoC;
import com.example.hw13.operations.order.InterpretableOrder;

import java.util.Objects;

public class WithArgs implements Expression {
    private final Object[] commandArgs;

    public WithArgs(Object[] commandArgs) {this.commandArgs = commandArgs;}

    @Override
    public Command interpret(UObject interpretableOrder) {
        var interpretableOrderAdapter = IoC.<InterpretableOrder>resolve("InterpretableOrderAdapter", interpretableOrder);
        var commandName = interpretableOrderAdapter
                .getCommandName()
                .orElse("EmptyCommand");
        if (Objects.isNull(commandArgs) || commandArgs.length == 0) {
            return IoC.resolve(commandName);
        }
        return IoC.resolve(commandName, commandArgs);
    }
}
