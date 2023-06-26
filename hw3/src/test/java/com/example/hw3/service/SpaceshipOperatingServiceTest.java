package com.example.hw3.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.read.ListAppender;
import com.example.hw3.command.log.LogErrorCommand;
import com.example.hw3.objects.SpaceshipObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
class SpaceshipOperatingServiceTest {
    private final SpaceshipOperatingService spaceshipService = new SpaceshipOperatingService();
    private final ListAppender listAppender = new ListAppender<>();
    private final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    private final SpaceshipObject spaceshipObject = SpaceshipObject.getInstance();

    @BeforeEach
    public void beforeTest() {
        listAppender.start();
        context.getLogger(LogErrorCommand.class).addAppender(listAppender);
    }

    @AfterEach
    public void afterTest() {
        spaceshipObject.setProperty("Position", null);
        spaceshipObject.setProperty("ShiftVector", null);
        spaceshipObject.setProperty("Velocity", null);
        spaceshipObject.setProperty("Direction", null);
        spaceshipObject.setProperty("DirectionsNumber", null);
        listAppender.stop();
    }

    @Test
    void shouldMoveSpaceship() {
        // given Position (12, 5) and shiftVector (-7, 3)
        var spaceshipObject = SpaceshipObject.getInstance();
        spaceshipObject.setProperty("Position", new double[]{12, 5});
        spaceshipObject.setProperty("ShiftVector", new double[]{-7, 3});
        double[] expectedPosition = new double[]{5, 8};

        // when move
        spaceshipService.move();

        // then should change position to (5, 8)
        double[] actualPosition = spaceshipService.getCurrentPosition();
        assertArrayEquals(actualPosition, expectedPosition, 0);
    }

    @Test
    void shouldThrowExceptionWhenMoveSpaceshipWithNoPosition() {
        // given
        var spaceshipObject = SpaceshipObject.getInstance();
        spaceshipObject.setProperty("ShiftVector", new double[]{-7, 3});

        // when
        spaceshipService.move();

        // then
        var logs = listAppender.list;
        assertAll(
                () -> assertEquals(1, logs.size()),
                () -> assertTrue(logs.get(0).toString().contains("verdict: error when try to move"))
        );
    }

    @Test
    void shouldThrowExceptionWhenMoveSpaceshipWithNoVelocity() {
        // given
        var spaceshipObject = SpaceshipObject.getInstance();
        spaceshipObject.setProperty("Position", new double[]{12, 5});
        spaceshipObject.setProperty("Direction", 3);
        spaceshipObject.setProperty("DirectionsNumber", 8);

        // when
        spaceshipService.move();

        // then
        var logs = listAppender.list;
        assertAll(
                () -> assertEquals(2, logs.size()),
                () -> assertTrue(logs.get(0).toString().contains("verdict: error when try to rotate"))
        );
    }

    @Test
    void shouldThrowExceptionWhenMoveSpaceshipWithNoDirection() {
        // given
        var spaceshipObject = SpaceshipObject.getInstance();
        spaceshipObject.setProperty("Position", new double[]{12, 5});
        spaceshipObject.setProperty("Velocity", 3);

        // when
        spaceshipService.move();

        // then
        var logs = listAppender.list;
        assertAll(
                () -> assertEquals(2, logs.size()),
                () -> assertTrue(logs.get(0).toString().contains("verdict: error when try to rotate"))
        );
    }
}
