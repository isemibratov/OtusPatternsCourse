package com.example.hw3.features;

import java.util.Optional;

public interface Movable {
    void move(double[] shiftVector);

    Optional<double[]> getPosition();

    void setPosition(double[] newValue);
}
