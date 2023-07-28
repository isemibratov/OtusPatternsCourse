package com.example.hw5.command.features;

import com.example.hw5.command.Command;
import com.example.hw5.exception.ExceptionHandler;
import com.example.hw5.features.RotatableAdapter;
import com.example.hw5.objects.UObject;

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
