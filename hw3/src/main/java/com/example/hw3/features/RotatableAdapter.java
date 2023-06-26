package com.example.hw3.features;

import com.example.hw3.objects.UObject;

import java.util.Optional;

public class RotatableAdapter implements Rotatable {
    private final UObject rotatableObject;

    public RotatableAdapter(UObject rotatableObject) {
        this.rotatableObject = rotatableObject;
    }

    @Override
    public void rotate() {
        try {
            int direction = getDirection()
                    .orElseThrow(IllegalStateException::new);
            int directionsNumber = getDirectionsNumber()
                    .orElseThrow(IllegalStateException::new);
            int velocity = getVelocity()
                    .orElseThrow(IllegalStateException::new);
            var shiftVector = new double[]{
                    velocity * Math.cos((double) direction / 360 * directionsNumber),
                    velocity * Math.sin((double) direction / 360 * directionsNumber)};
            setShiftVector(shiftVector);
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("error when try to rotate");
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

    @Override
    public Optional<double[]> getShiftVector() {
        return Optional.ofNullable((double[]) rotatableObject.getProperty("ShiftVector"));
    }

    @Override
    public void setShiftVector(double[] shiftVector) {
        rotatableObject.setProperty("ShiftVector", shiftVector);
    }
}
