package com.example.hw10.ioc.scope_based;

import com.example.hw10.command.Command;
import com.example.hw10.exception.exceptions.CommandException;

import java.util.function.Function;

public class RegisterIoCsDependencyCommand implements Command {
    private final String key;
    private final Function<Object[], Object> strategy;

    public RegisterIoCsDependencyCommand(String key, Function<Object[], Object> strategy) {
        this.key = key;
        this.strategy = strategy;
    }

    @Override
    public void execute() {
        try {
            ScopeBasedResolveDependencyStrategy
                    .getCurrentScopes()
                    .get()
                    .getDependencies()
                    .putIfAbsent(key, strategy);
        } catch (Exception ex) {
            throw new CommandException("Dependency registration error");
        }
    }
}
