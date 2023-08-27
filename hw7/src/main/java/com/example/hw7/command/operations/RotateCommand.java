package com.example.hw7.command.operations;

import com.example.hw7.command.Command;
import com.example.hw7.exception.ExceptionHandler;
import com.example.hw7.ioc.IoC;
import com.example.hw7.objects.UObject;
import com.example.hw7.operations.Rotatable;

public class RotateCommand implements Command {
    private final Rotatable rotatableAdapter;

    public RotateCommand(UObject rotatableObject) {
        this.rotatableAdapter = IoC.resolve("RotatableAdapter", rotatableObject);
    }

    @Override
    public void execute() {
        try {
            int direction = rotatableAdapter.getDirection()
                    .orElseThrow(IllegalStateException::new);
            int directionsNumber = rotatableAdapter.getDirectionsNumber()
                    .orElseThrow(IllegalStateException::new);
            int newDirection = 2 * direction % directionsNumber;
            rotatableAdapter.setDirection(newDirection);
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
