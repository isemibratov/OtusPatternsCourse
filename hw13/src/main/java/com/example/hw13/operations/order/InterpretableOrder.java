package com.example.hw13.operations.order;

import com.example.hw13.game_objects.UObject;

import java.util.Optional;

public interface InterpretableOrder {
    Optional<UObject> getSubject();

    void setSubject(UObject newValue);

    Optional<String> getCommandName();

    void setCommandName(String newValue);
}
