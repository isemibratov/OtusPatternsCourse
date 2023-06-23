package com.example.hw2.service;

import com.example.hw2.common.Vector;
import com.example.hw2.features.MovableAdapter;
import com.example.hw2.features.RotatableAdapter;
import com.example.hw2.objects.SpaceshipObject;

public class SpaceshipOperatingService {
    private final MovableAdapter movableAdapter;
    private final RotatableAdapter rotatableAdapter;

    public SpaceshipOperatingService(SpaceshipObject spaceship) {
        this.movableAdapter = new MovableAdapter(spaceship);
        this.rotatableAdapter = new RotatableAdapter(spaceship);
    }

    public void move() {
        Vector shiftVector = getShiftVector();
        move(shiftVector);
    }

    public void move(Vector shiftVector) {
        movableAdapter.move(shiftVector);
    }

    public Vector getShiftVector() {
        return rotatableAdapter.getShiftVector();
    }

    public Vector getCurrentPosition() {
        try {
            return movableAdapter.getPosition()
                    .orElseThrow(IllegalStateException::new);
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("error when try to get current position");
        }
    }
}
