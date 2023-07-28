package com.example.hw6.operations;

import java.util.Optional;

public interface Rotatable {
    Optional<double[]> getVelocityVector();

    void setVelocityVector(double[] velocityVector);

    Optional<Integer> getVelocity();

    Optional<Integer> getDirection();

    void setDirection(Integer newDirection);

    Optional<Integer> getDirectionsNumber();
}
