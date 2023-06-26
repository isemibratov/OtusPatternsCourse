package com.example.hw3.command.queue;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.hw3.queue.LinkedListCommandQueue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddElementInQueueCommandTest {
    @InjectMocks private AddElementInQueueCommand addElementInQueueCommand;

    @Test
    void shouldAddElementInQueue() {
        // given
        var queue = LinkedListCommandQueue.getInstance();

        // when
        var queueSizeBefore = queue.size();
        addElementInQueueCommand.execute();

        // then
        var queueSizeAfter = queue.size();
        assertAll(
                () -> assertEquals(0, queueSizeBefore),
                () -> assertEquals(1, queueSizeAfter)
        );
    }
}