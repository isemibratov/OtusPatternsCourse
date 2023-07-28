package com.example.hw5.command.features;

import com.example.hw5.command.Command;
import com.example.hw5.exception.ExceptionHandler;
import com.example.hw5.exception.exceptions.CheckFuelCommandException;
import com.example.hw5.exception.exceptions.CommandException;
import com.example.hw5.features.MovableAdapter;
import com.example.hw5.objects.UObject;

public class LinearMoveCommand implements Command {
    private final MovableAdapter movableAdapter;

    public LinearMoveCommand(UObject movableObject) {
        this.movableAdapter = new MovableAdapter(movableObject);
    }

    @Override
    public void execute() {
        try {
            var velocityVector = getLinearVelocityVector();
            movableAdapter.move(velocityVector);
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
            throw new CommandException("error when linear move. verdict: " + ex.getMessage());
        }
    }

    private double[] getLinearVelocityVector() {
        var velocity = movableAdapter.getVelocity()
                .orElseThrow(() -> new CheckFuelCommandException("no object velocity found"));
        return new double[]{velocity, 0};
    }
}
