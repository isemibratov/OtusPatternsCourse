package com.example.hw4.command.features;

import com.example.hw4.command.Command;
import com.example.hw4.features.RotatableAdapter;
import com.example.hw4.objects.UObject;

public class ChangeVelocityCommand implements Command {
    private final RotatableAdapter rotatableAdapter;

    public ChangeVelocityCommand(UObject rotatableObject) {
        this.rotatableAdapter = new RotatableAdapter(rotatableObject);
    }

    @Override
    public void execute() {
        rotatableAdapter.setVelocityVector();
    }
}
