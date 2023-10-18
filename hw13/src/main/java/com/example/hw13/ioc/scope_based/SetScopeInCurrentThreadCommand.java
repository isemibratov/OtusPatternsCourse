package com.example.hw13.ioc.scope_based;

import com.example.hw13.command.Command;

class SetScopeInCurrentThreadCommand implements Command {
    private final ScopeImpl scope;

    public SetScopeInCurrentThreadCommand(ScopeImpl scope) {
        this.scope = scope;
    }

    @Override
    public void execute() {
        ScopeBasedResolveDependencyStrategy.getCurrentScopes().set(scope);
    }
}
