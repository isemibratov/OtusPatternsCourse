package com.example.hw13.ioc;

import com.example.hw13.command.Command;

import java.util.function.BiFunction;

public class SetupStrategyCommand implements Command {
    private final BiFunction<String, Object[], Object> newStrategy;

    public SetupStrategyCommand(BiFunction<String, Object[], Object> newStrategy) {
        this.newStrategy = newStrategy;
    }

    @Override
    public void execute() {
        IoC.setStrategy(newStrategy);
    }
}
