package com.example.hw4.command.features;

import com.example.hw4.command.Command;
import com.example.hw4.exception.ExceptionHandler;
import com.example.hw4.features.RotatableAdapter;
import com.example.hw4.objects.UObject;

public class RotateCommand implements Command {
    private final RotatableAdapter rotatableAdapter;

    public RotateCommand(UObject movableObject) {
        this.rotatableAdapter = new RotatableAdapter(movableObject);
    }

    @Override
    public void execute() {
        try {
            rotatableAdapter.rotate();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
