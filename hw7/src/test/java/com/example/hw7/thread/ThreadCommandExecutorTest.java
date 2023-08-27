package com.example.hw7.thread;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.example.hw7.command.Command;
import com.example.hw7.command.thread.HardStopCommand;
import com.example.hw7.command.thread.InitCommand;
import com.example.hw7.command.thread.SoftStopCommand;
import com.example.hw7.ioc.scope_based.InitScopeBasedIoCImplCommand;
import com.example.hw7.objects.ContextObject;
import com.example.hw7.queue.LinkedListCommandQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ThreadCommandExecutorTest {
    @BeforeEach
    void setup() {
        new InitScopeBasedIoCImplCommand().execute();
    }

    @Test
    void shouldStopProcessorImmediatelyWhenHardStopCommand() throws InterruptedException {
        // given
        var context = ContextObject.getInstance();
        new InitCommand().execute();
        var queue = (LinkedListCommandQueue) context.getProperty("queue");

        var mockedCommand = mock(Command.class);
        queue.push(mockedCommand);
        queue.push(new HardStopCommand());
        queue.push(mockedCommand);

        // when
        var processor = new Processor(new ProcessableImpl());
        processor.waitThread();

        // then
        assertEquals(1, queue.size());
    }

    @Test
    void shouldStopProcessorWhenQueueIsEmptyWhenSoftStopCommand() throws InterruptedException {
        // given
        var context = ContextObject.getInstance();
        new InitCommand().execute();
        var queue = (LinkedListCommandQueue) context.getProperty("queue");

        var mockedCommand = mock(Command.class);
        queue.push(mockedCommand);
        queue.push(new SoftStopCommand());
        queue.push(mockedCommand);

        // when
        var processor = new Processor(new ProcessableImpl());
        processor.waitThread();

        // then
        assertEquals(0, queue.size());
    }
}
