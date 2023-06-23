package com.example.hw2.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.hw2.common.Vector;
import com.example.hw2.objects.SpaceshipObject;
import org.junit.Test;

public class SpaceshipOperatingServiceTest {

    @Test
    public void shouldMoveSpaceship() {
        // given Position (12, 5) and shiftVector (-7, 3)
        var spaceshipObject = new SpaceshipObject();
        spaceshipObject.setProperty("Position", new Vector(12, 5));
        var spaceshipService = new SpaceshipOperatingService(spaceshipObject);
        Vector shiftVector = new Vector(-7, 3);
        Vector expectedPosition = new Vector(5, 8);

        // when move
        spaceshipService.move(shiftVector);

        // then should change position to (5, 8)
        Vector actualPosition = spaceshipService.getCurrentPosition();
        assertAll(
                () -> assertEquals(expectedPosition.getX(), actualPosition.getX()),
                () -> assertEquals(expectedPosition.getY(), actualPosition.getY())
        );
    }

    @Test
    public void shouldThrowExceptionWhenMoveSpaceshipWithNoPosition() {
        // given
        var spaceshipService = new SpaceshipOperatingService(new SpaceshipObject());
        Vector shiftVector = new Vector(-7, 3);

        // when+then
        assertThrows(
                IllegalStateException.class,
                () -> spaceshipService.move(shiftVector));
    }

    @Test
    public void shouldThrowExceptionWhenMoveSpaceshipWithNoVelocity() {
        // given
        var spaceshipObject = new SpaceshipObject();
        spaceshipObject.setProperty("Position", new Vector(12, 5));
        spaceshipObject.setProperty("Direction", 3);
        spaceshipObject.setProperty("DirectionsNumber", 8);
        var spaceshipService = new SpaceshipOperatingService(spaceshipObject);

        // when+then
        assertThrows(
                IllegalStateException.class,
                spaceshipService::move);
    }

    @Test
    public void shouldThrowExceptionWhenMoveSpaceshipWithNoDirection() {
        // given
        var spaceshipObject = new SpaceshipObject();
        spaceshipObject.setProperty("Position", new Vector(12, 5));
        spaceshipObject.setProperty("Velocity", 3);
        var spaceshipService = new SpaceshipOperatingService(spaceshipObject);

        // when+then
        assertThrows(
                IllegalStateException.class,
                spaceshipService::move);
    }
}
