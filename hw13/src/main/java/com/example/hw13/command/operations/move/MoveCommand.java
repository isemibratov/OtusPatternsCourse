package com.example.hw13.command.operations.move;

import com.example.hw13.command.Command;
import com.example.hw13.ioc.IoC;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.operations.Movable;
import com.example.hw13.utils.VectorUtils;

public class MoveCommand implements Command {
    private final Movable movableAdapter;
    private final double[] velocityVector;

    public MoveCommand(UObject movableObject, double[] velocityVector) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
        this.velocityVector = velocityVector;
    }

    @Override
    public void execute() {
        try {
            double[] currentPosition = movableAdapter.getPosition()
                    .orElseThrow(IllegalStateException::new);
            movableAdapter.setPosition(VectorUtils.add(currentPosition, velocityVector));
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("error when try to move");
        }
    }
}
