package com.example.hw6.operations;

import com.example.hw6.command.injectable.Injectable;

import java.util.Optional;

public interface Movable {
    Optional<double[]> getPosition();

    void setPosition(double[] newValue);

    Optional<Integer> getVelocity();

    Optional<Injectable> getMovement();

    void setMovement(Injectable injectable);

    void finish();
}
