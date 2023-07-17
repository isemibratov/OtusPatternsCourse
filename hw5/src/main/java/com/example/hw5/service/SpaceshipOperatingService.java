package com.example.hw5.service;

import com.example.hw5.command.features.MoveCommand;
import com.example.hw5.command.features.RotateCommand;
import com.example.hw5.features.MovableAdapter;
import com.example.hw5.objects.SpaceshipObject;

public class SpaceshipOperatingService {
    private final SpaceshipObject spaceshipObject;
    private final MovableAdapter movableAdapter;

    public SpaceshipOperatingService() {
        this.spaceshipObject = SpaceshipObject.getInstance();
        this.movableAdapter = new MovableAdapter(spaceshipObject);
    }

    public void move() {
        new MoveCommand(spaceshipObject).execute();
    }

    public void rotate() {
        new RotateCommand(spaceshipObject).execute();
    }

    public double[] getCurrentPosition() {
        try {
            return movableAdapter.getPosition()
                    .orElseThrow(IllegalStateException::new);
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("error when try to get current position");
        }
    }
}
