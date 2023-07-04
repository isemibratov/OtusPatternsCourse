package com.example.hw4.command.features;

import static org.junit.jupiter.api.Assertions.*;

import com.example.hw4.exception.exceptions.CommandException;
import com.example.hw4.objects.SpaceshipObject;
import com.example.hw4.objects.UObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class BurnFuelCommandTest {
    private UObject object;

    @BeforeEach
    public void beforeTest() {
        object = new SpaceshipObject();
    }

    @Test
    void shouldSuccessfulBurnFuel() {
        // given
        object.setProperty("Fuel", 2.0);
        object.setProperty("FuelConsumption", 1.0);

        // when+then
        assertDoesNotThrow(() -> new CheckFuelCommand(object).execute());
    }

    @ParameterizedTest
    @MethodSource("getNullParams")
    void shouldThrowCommandExceptionWhenBurnFuelIfParamsNotFound(Double fuel, Double fuelConsumption) {
        // given
        object.setProperty("Fuel", fuel);
        object.setProperty("FuelConsumption", fuelConsumption);
        var command = new CheckFuelCommand(object);

        // when+then
        assertThrows(
                CommandException.class,
                command::execute);
    }

    private static Stream<Arguments> getNullParams() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(1.0, null),
                Arguments.of(null, 1.0)
        );
    }
}