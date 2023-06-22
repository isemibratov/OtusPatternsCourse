package com.example.hw2.features;

import com.example.hw2.common.Vector;

import java.util.Optional;

public interface Movable {
    void move(Vector shiftVector);
    Optional<Vector> getPosition();
    void setPosition(Vector newValue);
}
