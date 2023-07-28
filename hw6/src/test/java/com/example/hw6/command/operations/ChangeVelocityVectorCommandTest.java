package com.example.hw6.command.operations;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.hw6.IoCAbstractTest;
import com.example.hw6.ioc.IoC;
import com.example.hw6.objects.SpaceshipObject;
import com.example.hw6.objects.UObject;
import com.example.hw6.operations.Rotatable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChangeVelocityVectorCommandTest extends IoCAbstractTest {
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
        new ChangeVelocityVectorCommand(spaceship).execute();

        // then
        var actualVelocityVector = IoC.<Rotatable>resolve("RotatableAdapter", spaceship).getVelocityVector();
        assertAll(
                () -> assertTrue(actualVelocityVector.isPresent()),
                () -> assertArrayEquals(expectedVelocityVector, actualVelocityVector.get())
        );
    }
}