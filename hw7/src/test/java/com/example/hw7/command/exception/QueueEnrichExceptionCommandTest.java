package com.example.hw7.command.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;

import com.example.hw7.command.queue.AddElementInQueueCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class QueueEnrichExceptionCommandTest {
    @InjectMocks private QueueEnrichExceptionCommand queueEnrichExceptionCommand;

    @Test
    void shouldAddElementInQueueCommandWhenHandleException() {
        try (MockedConstruction<AddElementInQueueCommand> addElementInQueueCommandConstructionMock = mockConstruction(AddElementInQueueCommand.class)) {
            // when
            queueEnrichExceptionCommand.execute();

            // then
            List<AddElementInQueueCommand> constructed = addElementInQueueCommandConstructionMock.constructed();
            assertEquals(1, constructed.size());
            var addElementInQueueCommandMock = constructed.get(0);
            verify(addElementInQueueCommandMock)
                    .execute();
        }
    }
}