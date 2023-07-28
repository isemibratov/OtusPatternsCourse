package com.example.hw5.features;

import java.util.Optional;

public interface Rotatable {
    void rotate();

    Optional<double[]> getVelocityVector();

    void setVelocityVector(double[] velocityVector);

    void setVelocityVector();

    Optional<Integer> getVelocity();

    Optional<Integer> getDirection();

    void setDirection(Integer newDirection);

    Optional<Integer> getDirectionsNumber();
}
