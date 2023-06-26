package com.example.hw3.command.features;

import com.example.hw3.command.Command;
import com.example.hw3.exception.ExceptionHandler;
import com.example.hw3.features.MovableAdapter;
import com.example.hw3.features.RotatableAdapter;
import com.example.hw3.objects.UObject;

public class MoveCommand implements Command {
    private final UObject movableObject;
    private final MovableAdapter movableAdapter;
    private final RotatableAdapter rotatableAdapter;

    public MoveCommand(UObject movableObject) {
        this.movableObject = movableObject;
        this.movableAdapter = new MovableAdapter(movableObject);
        this.rotatableAdapter = new RotatableAdapter(movableObject);
    }

    @Override
    public void execute() {
        try {
            var shiftVector = rotateIfNeedAndReturnShiftVector();
            movableAdapter.move(shiftVector);
        } catch(Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }

    private double[] rotateIfNeedAndReturnShiftVector() {
        try {
            var shiftVectorOptional = rotatableAdapter.getShiftVector();
            if (shiftVectorOptional.isEmpty()) {
                new RotateCommand(movableObject).execute();
                return rotatableAdapter
                        .getShiftVector()
                        .orElseThrow(IllegalStateException::new);
            } else {
                return shiftVectorOptional.get();
            }
        } catch(Exception ex) {
            throw new IllegalStateException("error when try to rotate");
        }
    }
}
