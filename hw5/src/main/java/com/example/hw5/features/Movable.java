package com.example.hw5.features;

import java.util.Optional;

public interface Movable {
    void move(double[] velocityVector);

    Optional<double[]> getPosition();

    Optional<Integer> getVelocity();

    void setPosition(double[] newValue);
}
