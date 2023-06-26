package com.example.hw3.features;

import java.util.Optional;

public interface Rotatable {
    void rotate();
    Optional<double[]> getShiftVector();

    void setShiftVector(double[] shiftVector);

    Optional<Integer> getVelocity();

    Optional<Integer> getDirection();

    Optional<Integer> getDirectionsNumber();
}
