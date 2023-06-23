package com.example.hw2.features;

import com.example.hw2.common.Vector;

import java.util.Optional;

public interface Rotatable {
    Vector getShiftVector();
    Optional<Integer> getVelocity();
    Optional<Integer> getDirection();
    Optional<Integer> getDirectionsNumber();
}
