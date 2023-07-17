package com.example.hw5.command.features;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.hw5.features.RotatableAdapter;
import com.example.hw5.objects.SpaceshipObject;
import com.example.hw5.objects.UObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChangeVelocityCommandTest {
    private UObject spaceship;

    @BeforeEach
    public void beforeTest() {
        spaceship = new SpaceshipObject();
    }

    @Test
    void shouldSuccessfulChangeVelocity() {
        // given
        int velocity = 3;
        int direction = 3;
        int directionsNumber = 8;
        spaceship.setProperty("Velocity", velocity);
        spaceship.setProperty("Direction", direction);
        spaceship.setProperty("DirectionsNumber", directionsNumber);
        spaceship.setProperty("VelocityVector", new double[]{0, 0});
        var expectedVelocityVector = new double[] {
                velocity * Math.cos((double) direction / 360 * directionsNumber),
                velocity * Math.sin((double) direction / 360 * directionsNumber)
        };

        // when
        new ChangeVelocityCommand(spaceship).execute();

        // then
        var actualVelocityVector = new RotatableAdapter(spaceship).getVelocityVector();
        assertAll(
                () -> assertTrue(actualVelocityVector.isPresent()),
                () -> assertArrayEquals(expectedVelocityVector, actualVelocityVector.get())
        );
    }
}