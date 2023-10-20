package com.example.hw13.command.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.hw13.command.Command;
import com.example.hw13.command.log.LogErrorCommand;
import com.example.hw13.queue.LinkedListCommandQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TryTwiceOrLogExceptionCommandTest {
    @Mock private Command command;
    @InjectMocks private TryTwiceOrLogExceptionCommand tryTwiceOrLogExceptionCommand;

    @BeforeEach
    public void beforeTest() {
        LinkedListCommandQueue.getInstance().push(command);
    }

    @AfterEach
    public void afterTest() {
        LinkedListCommandQueue.getInstance().clear();
    }

    @Test
    void shouldTryTwiceOrLogHandleException() {
        try (MockedConstruction<LogErrorCommand> logErrorCommandConstructionMock = mockConstruction(LogErrorCommand.class)) {
            doThrow(new RuntimeException("ex"))
                    .when(command)
                            .execute();

            // when
            tryTwiceOrLogExceptionCommand.execute();

            // then
            List<LogErrorCommand> constructed = logErrorCommandConstructionMock.constructed();
            assertEquals(1, constructed.size());
            var logErrorCommandMock = constructed.get(0);
            verify(command, times(2))
                    .execute();
            verify(logErrorCommandMock)
                    .execute();
        }
    }

    @Test
    void shouldNotLogErrorIfRerunSuccessfulHandleException() {
        try (MockedConstruction<LogErrorCommand> logErrorCommandConstructionMock = mockConstruction(LogErrorCommand.class)) {
            doThrow(new RuntimeException("ex"))
                    .doNothing()
                    .when(command)
                    .execute();

            // when
            tryTwiceOrLogExceptionCommand.execute();

            // then
            List<LogErrorCommand> constructed = logErrorCommandConstructionMock.constructed();
            assertEquals(0, constructed.size());
            verify(command, times(2))
                    .execute();
        }
    }

}