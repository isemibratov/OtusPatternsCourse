package com.example.hw5.command.log;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.read.ListAppender;
import com.example.hw5.command.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
class LogErrorCommandTest {
    @Mock private Command command;
    @Mock private Exception exception;
    @InjectMocks private LogErrorCommand logErrorCommand;

    @Test
    void shouldLogError() {
        // given
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        var listAppender = new ListAppender();
        listAppender.start();
        context.getLogger(LogErrorCommand.class).addAppender(listAppender);

        var exceptionMessage = "exceptionMessage";
        when(exception.getMessage())
                .thenReturn(exceptionMessage);

        // when
        logErrorCommand.execute();

        // verify
        var logs = listAppender.list;
        assertAll(
                () -> assertEquals(1, logs.size()),
                () -> assertTrue(logs.get(0).toString().contains(exceptionMessage))
        );
    }
}