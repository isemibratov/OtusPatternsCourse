package com.example.hw6.command.operations.macro;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.hw6.IoCAbstractTest;
import com.example.hw6.exception.exceptions.CommandException;
import com.example.hw6.ioc.adapter.InitAdapterGeneratorCommand;
import com.example.hw6.ioc.scope_based.InitScopeBasedIoCImplCommand;
import com.example.hw6.objects.SpaceshipObject;
import com.example.hw6.objects.UObject;
import com.example.hw6.operations.Movable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LinearMoveWithFuelConsumptionMacroCommandTest extends IoCAbstractTest {
    private UObject object;

    private static Stream<Arguments> getNullFuelParams() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(1.0, null),
                Arguments.of(null, 1.0)
        );
    }

    @BeforeEach
    public void beforeTest() {
        object = new SpaceshipObject();
    }

    @Test
    void shouldSuccessfulMoveWithFuelConsumption() {
        // given
        object.setProperty("Fuel", 2.0);
        object.setProperty("FuelConsumption", 1.0);
        object.setProperty("Position", new double[]{12, 5});
        object.setProperty("Velocity", 3);
        var expectedPosition = new double[]{15, 5};
        var expectedFuel = 1.0;

        // when
        new LinearMoveWithFuelConsumptionMacroCommand(object).execute();

        // then
        var actualPosition = (double[]) object.getProperty("Position");
        var actualFuel = (double) object.getProperty("Fuel");
        assertAll(
                () -> assertArrayEquals(expectedPosition, actualPosition),
                () -> assertEquals(expectedFuel, actualFuel)
        );
    }

    @ParameterizedTest
    @MethodSource("getNullFuelParams")
    void shouldThrowCommandExceptionWhenCheckFuelProblem(Double fuel, Double fuelConsumption) {
        // given
        object.setProperty("Fuel", fuel);
        object.setProperty("FuelConsumption", fuelConsumption);
        object.setProperty("Position", new double[]{12, 5});
        object.setProperty("Velocity", 3);
        var command = new LinearMoveWithFuelConsumptionMacroCommand(object);

        // when+then
        assertThrows(
                CommandException.class,
                command::execute);
    }

    @Test
    void shouldThrowCommandExceptionWhenVelocityNotFoundProblem() {
        // given
        object.setProperty("Fuel", 2.0);
        object.setProperty("FuelConsumption", 1.0);
        object.setProperty("Position", new double[]{12, 5});
        object.setProperty("Velocity", null);
        var command = new LinearMoveWithFuelConsumptionMacroCommand(object);

        // when+then
        assertThrows(
                CommandException.class,
                command::execute);
    }

    @Test
    void shouldThrowCommandExceptionWhenPositionNotFoundProblem() {
        // given
        object.setProperty("Fuel", 2.0);
        object.setProperty("FuelConsumption", 1.0);
        object.setProperty("Position", null);
        object.setProperty("Velocity", 3);
        var command = new LinearMoveWithFuelConsumptionMacroCommand(object);

        // when+then
        assertThrows(
                CommandException.class,
                command::execute);
    }
}