package com.example.hw12.command.operations;

import com.example.hw12.command.Command;
import com.example.hw12.exception.ExceptionHandler;
import com.example.hw12.ioc.IoC;
import com.example.hw12.game_objects.UObject;
import com.example.hw12.operations.Rotatable;

public class ChangeVelocityVectorCommand implements Command {
    private final Rotatable rotatableAdapter;

    public ChangeVelocityVectorCommand(UObject rotatableObject) {
        this.rotatableAdapter = IoC.resolve("RotatableAdapter", rotatableObject);
    }

    @Override
    public void execute() {
        try {
            int direction = rotatableAdapter.getDirection()
                    .orElseThrow(IllegalStateException::new);
            int directionsNumber = rotatableAdapter.getDirectionsNumber()
                    .orElseThrow(IllegalStateException::new);
            int velocity = rotatableAdapter.getVelocity()
                    .orElseThrow(IllegalStateException::new);
            var velocityVector = new double[]{
                    velocity * Math.cos((double) direction / 360 * directionsNumber),
                    velocity * Math.sin((double) direction / 360 * directionsNumber)};
            rotatableAdapter.setVelocityVector(velocityVector);
        } catch (IllegalStateException ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
            throw new IllegalStateException("error when try to rotate");
        }
    }
}
