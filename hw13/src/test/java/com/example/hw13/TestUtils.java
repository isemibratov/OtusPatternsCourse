package com.example.hw13;

import com.example.hw13.game_objects.spaceship.SpaceshipObject;

public class TestUtils {
    public static void clearSpaceshipObject() {
        var spaceshipObject = SpaceshipObject.getInstance();
        spaceshipObject.setProperty("Position", null);
        spaceshipObject.setProperty("VelocityVector", null);
        spaceshipObject.setProperty("Velocity", null);
        spaceshipObject.setProperty("Direction", null);
        spaceshipObject.setProperty("DirectionsNumber", null);
    }
}
