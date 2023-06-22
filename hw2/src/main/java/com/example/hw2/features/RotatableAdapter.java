package com.example.hw2.features;

import com.example.hw2.common.Vector;
import com.example.hw2.objects.UObject;

import java.util.Optional;

public class RotatableAdapter implements Rotatable {
    private final UObject rotatableObject;

    public RotatableAdapter(UObject rotatableObject) {
        this.rotatableObject = rotatableObject;
    }

    @Override
    public Vector getShiftVector() {
        try {
            int direction = getDirection()
                    .orElseThrow(IllegalStateException::new);
            int directionsNumber = getDirectionsNumber()
                    .orElseThrow(IllegalStateException::new);
            int velocity = getVelocity()
                    .orElseThrow(IllegalStateException::new);
            return new Vector(
                    velocity * Math.cos((double) direction / 360 * directionsNumber),
                    velocity * Math.sin((double) direction / 360 * directionsNumber));
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("error when try to get shift vector");
        }
    }

    @Override
    public Optional<Integer> getVelocity() {
        return Optional.ofNullable((Integer) rotatableObject.getProperty("Velocity"));
    }

    @Override
    public Optional<Integer> getDirection() {
        return Optional.ofNullable((Integer) rotatableObject.getProperty("Direction"));
    }

    @Override
    public Optional<Integer> getDirectionsNumber() {
        return Optional.ofNullable((Integer) rotatableObject.getProperty("DirectionsNumber"));
    }
}
