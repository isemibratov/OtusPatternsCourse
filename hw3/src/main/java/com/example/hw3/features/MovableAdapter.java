package com.example.hw3.features;

import com.example.hw3.utils.VectorUtils;
import com.example.hw3.objects.UObject;

import java.util.Optional;

public class MovableAdapter implements Movable {
    private final UObject movableObject;

    public MovableAdapter(UObject movableObject) {
        this.movableObject = movableObject;
    }

    @Override
    public void move(double[] shiftVector) {
        try {
            double[] currentPosition = getPosition()
                    .orElseThrow(IllegalStateException::new);
            setPosition(VectorUtils.add(currentPosition, shiftVector));
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("error when try to move");
        }
    }

    @Override
    public Optional<double[]> getPosition() {
        return Optional.ofNullable((double[]) movableObject.getProperty("Position"));
    }

    @Override
    public void setPosition(double[] newValue) {
        movableObject.setProperty("Position", newValue);
    }
}
