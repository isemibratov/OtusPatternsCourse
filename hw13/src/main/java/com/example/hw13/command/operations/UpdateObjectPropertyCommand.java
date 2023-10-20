package com.example.hw13.command.operations;

import com.example.hw13.command.Command;
import com.example.hw13.game_objects.UObject;

public class UpdateObjectPropertyCommand implements Command {
    private final UObject uObject;
    private final String key;
    private final Object newValue;

    public UpdateObjectPropertyCommand(UObject uObject, String key, Object newValue) {
        this.uObject = uObject;
        this.key = key;
        this.newValue = newValue;
    }

    @Override
    public void execute() {
        uObject.setProperty(key, newValue);
    }
}
