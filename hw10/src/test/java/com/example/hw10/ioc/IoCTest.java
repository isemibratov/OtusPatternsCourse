package com.example.hw10.ioc;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.hw10.command.Command;
import com.example.hw10.ioc.adapter.InitAdapterGeneratorCommand;
import com.example.hw10.ioc.scope_based.InitScopeBasedIoCImplCommand;
import com.example.hw10.objects.SpaceshipObject;
import com.example.hw10.operations.Movable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

class IoCTest {
    private Object rootScope;

    @BeforeEach
    void setup() {
        new InitScopeBasedIoCImplCommand().execute();
        rootScope = IoC.resolve("Scopes.Current");
    }

    @Test
    void shouldIoC_CanRegisterNewDependency() {
        // given
        var mockedCommand = mock(Command.class);
        doNothing()
                .when(mockedCommand)
                .execute();

        // when
        IoC.<Command>resolve(
                        "IoC.Register",
                        "Queue.Log.Size",
                        (Function<Object[], Object>) (args) -> mockedCommand)
                .execute();

        // then
        IoC.<Command>resolve("Queue.Log.Size")
                .execute();
        await().atMost(1, SECONDS)
                .untilAsserted(() -> verify(mockedCommand).execute());
    }

    @Test
    void shouldIoC_CanCreateNewScope() {
        // when
        var newScope = IoC.resolve("Scopes.New", rootScope);

        // then
        var currentScope = IoC.resolve("Scopes.Current");
        assertAll(
                () -> assertEquals(rootScope, currentScope),
                () -> assertNotEquals(currentScope, newScope)
        );
    }

    @Test
    void shouldIoC_CanChangeCurrentScope() {
        // when
        var newScope = IoC.resolve("Scopes.New", rootScope);
        IoC.<Command>resolve("Scopes.Current.Set", newScope)
                .execute();

        // then
        var currentScope = IoC.resolve("Scopes.Current");
        assertEquals(currentScope, newScope);
    }

    @Test
    void shouldIoC_CanGenerateMovableAdapter() {
        // given
        var expectedPosition = new double[]{12, 5};
        var expectedVelocity = 1;
        var spaceshipObject = SpaceshipObject.getInstance();
        spaceshipObject.setProperty("Position", expectedPosition);
        spaceshipObject.setProperty("Velocity", expectedVelocity);
        new InitAdapterGeneratorCommand(Movable.class).execute();

        // when
        var actualAdapter = IoC.<Movable>resolve("MovableAdapter", spaceshipObject);

        // then
        var actualPosition = actualAdapter.getPosition();
        var actualVelocity = actualAdapter.getVelocity();
        actualAdapter.finish();

        assertAll(
                () -> assertNotNull(actualPosition),
                () -> assertEquals(expectedPosition, actualPosition.get()),
                () -> assertNotNull(actualVelocity),
                () -> assertEquals(expectedVelocity, actualVelocity.get())
        );
        var updatedPosition = new double[]{1, 1};
        actualAdapter.setPosition(updatedPosition);
        assertEquals(updatedPosition, actualAdapter.getPosition().get());
    }
}