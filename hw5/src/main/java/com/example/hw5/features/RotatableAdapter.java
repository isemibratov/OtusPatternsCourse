package com.example.hw5.features;

import com.example.hw5.objects.UObject;

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
            int newDirection = 2 * direction % directionsNumber;
            setDirection(newDirection);
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("error when try to rotate");
        }
    }

    @Override
    public Optional<Integer> getDirection() {
        return Optional.ofNullable((Integer) rotatableObject.getProperty("Direction"));
    }

    @Override
    public void setDirection(Integer newDirection) {
        rotatableObject.setProperty("Direction", newDirection);
    }

    @Override
    public Optional<Integer> getDirectionsNumber() {
        return Optional.ofNullable((Integer) rotatableObject.getProperty("DirectionsNumber"));
    }

    @Override
    public Optional<double[]> getVelocityVector() {
        return Optional.ofNullable((double[]) rotatableObject.getProperty("VelocityVector"));
    }

    @Override
    public void setVelocityVector(double[] velocityVector) {
        rotatableObject.setProperty("VelocityVector", velocityVector);
    }

    @Override
    public void setVelocityVector() {
        try {
            int direction = getDirection()
                    .orElseThrow(IllegalStateException::new);
            int directionsNumber = getDirectionsNumber()
                    .orElseThrow(IllegalStateException::new);
            int velocity = getVelocity()
                    .orElseThrow(IllegalStateException::new);
            var velocityVector = new double[]{
                    velocity * Math.cos((double) direction / 360 * directionsNumber),
                    velocity * Math.sin((double) direction / 360 * directionsNumber)};
            setVelocityVector(velocityVector);
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("error when try to rotate");
        }
    }

    @Override
    public Optional<Integer> getVelocity() {
        return Optional.ofNullable((Integer) rotatableObject.getProperty("Velocity"));
    }
}
