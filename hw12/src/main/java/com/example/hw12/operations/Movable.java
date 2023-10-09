package com.example.hw12.operations;

import com.example.hw12.command.injectable.Injectable;

import java.util.Optional;

public interface Movable {

    Optional<double[]> getPosition();

    void setPosition(double[] newValue);

    Optional<Integer> getVelocity();

    Optional<Injectable> getMovement();

    void setMovement(Injectable injectable);

    void finish();
}
