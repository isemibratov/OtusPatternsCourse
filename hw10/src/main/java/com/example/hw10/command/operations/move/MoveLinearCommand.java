package com.example.hw10.command.operations.move;

import com.example.hw10.command.Command;
import com.example.hw10.exception.ExceptionHandler;
import com.example.hw10.exception.exceptions.CheckFuelCommandException;
import com.example.hw10.exception.exceptions.CommandException;
import com.example.hw10.ioc.IoC;
import com.example.hw10.objects.UObject;
import com.example.hw10.operations.Movable;

public class MoveLinearCommand implements Command {
    private final UObject movableObject;
    private final Movable movableAdapter;

    public MoveLinearCommand(UObject movableObject) {
        this.movableObject = movableObject;
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
    }

    @Override
    public void execute() {
        try {
            var velocityVector = getLinearVelocityVector();
            new MoveCommand(movableObject, velocityVector).execute();
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
