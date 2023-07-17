package com.example.hw5.command.features;

import com.example.hw5.command.Command;
import com.example.hw5.command.features.macro.RotateThenChangeVelocityVectorMacroCommand;
import com.example.hw5.exception.ExceptionHandler;
import com.example.hw5.features.MovableAdapter;
import com.example.hw5.features.RotatableAdapter;
import com.example.hw5.objects.UObject;

public class MoveCommand implements Command {
    private final UObject movableAndRotatableObject;
    private final MovableAdapter movableAdapter;
    private final RotatableAdapter rotatableAdapter;

    public MoveCommand(UObject movableAndRotatableObject) {
        this.movableAndRotatableObject = movableAndRotatableObject;
        this.movableAdapter = new MovableAdapter(movableAndRotatableObject);
        this.rotatableAdapter = new RotatableAdapter(movableAndRotatableObject);
    }

    @Override
    public void execute() {
        try {
            var velocityVector = getVelocityVector();
            movableAdapter.move(velocityVector);
        } catch(Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }

    private double[] getVelocityVector() {
        try {
            var velocityVectorOptional = rotatableAdapter.getVelocityVector();
            if (velocityVectorOptional.isEmpty()) {
                new RotateThenChangeVelocityVectorMacroCommand(movableAndRotatableObject).execute();
                return rotatableAdapter
                        .getVelocityVector()
                        .orElseThrow(IllegalStateException::new);
            } else {
                return velocityVectorOptional.get();
            }
        } catch(Exception ex) {
            throw new IllegalStateException("error when try to rotate");
        }
    }
}
