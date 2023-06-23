package com.example.hw2.features;

import com.example.hw2.common.Vector;
import com.example.hw2.objects.UObject;

import java.util.Optional;

public class MovableAdapter implements Movable {
    private final UObject movableObject;

    public MovableAdapter(UObject movableObject) {
        this.movableObject = movableObject;
    }

    @Override
    public void move(Vector shiftVector) {
        try {
            Vector currentPosition = getPosition()
                    .orElseThrow(IllegalStateException::new);
            setPosition(
                    new Vector(
                            currentPosition.getX() + shiftVector.getX(),
                            currentPosition.getY() + shiftVector.getY()));
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("error when try to move");
        }
    }

    @Override
    public Optional<Vector> getPosition() {
        return Optional.ofNullable((Vector) movableObject.getProperty("Position"));
    }

    @Override
    public void setPosition(Vector newValue) {
        movableObject.setProperty("Position", newValue);
    }
}
