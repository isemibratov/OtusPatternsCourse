package com.example.hw13.interpreter;

import com.example.hw13.command.Command;
import com.example.hw13.game_objects.UObject;

public interface Expression {
    Command interpret(UObject order);
}
