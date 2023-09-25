package com.example.hw10.thread;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.hw10.command.Command;
import com.example.hw10.command.state.MoveToCommand;
import com.example.hw10.command.state.RunCommand;
import com.example.hw10.command.thread.HardStopCommand;
import com.example.hw10.command.thread.InitCommand;
import com.example.hw10.command.thread.SoftStopCommand;
import com.example.hw10.ioc.scope_based.InitScopeBasedIoCImplCommand;
import com.example.hw10.objects.ContextObject;
import com.example.hw10.queue.LinkedListCommandQueue;
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

    @Test
    void shouldNormalStateByDefault() throws InterruptedException {
        // given
        var context = ContextObject.getInstance();
        new InitCommand().execute();
        var queue = (LinkedListCommandQueue) context.getProperty("queue");

        var mockedCommand = mock(Command.class);
        queue.push(mockedCommand);
        queue.push(new HardStopCommand());

        // when
        var processor = new Processor(new ProcessableImpl());
        processor.waitThread();

        // then
        assertEquals(1, queue.size());
        assertNull(context.getProperty("newQueue"));
    }

    @Test
    void shouldMoveQueueWhenMoveToCommand() throws InterruptedException {
        // given
        var context = ContextObject.getInstance();
        new InitCommand().execute();
        var queue = (LinkedListCommandQueue) context.getProperty("queue");

        var mockedCommand = mock(Command.class);
        queue.push(mockedCommand);
        queue.push(new HardStopCommand());
        queue.push(new MoveToCommand());

        // when
        var processor = new Processor(new ProcessableImpl());
        processor.waitThread();

        // then
        var newQueue = (LinkedListCommandQueue) context.getProperty("newQueue");
        assertEquals(0, queue.size());
        assertEquals(2, newQueue.size());
    }

    @Test
    void shouldProcessQueueAsNormalWhenRunCommand() throws InterruptedException {
        // given
        var context = ContextObject.getInstance();
        new InitCommand().execute();
        var queue = (LinkedListCommandQueue) context.getProperty("queue");

        var mockedCommand = mock(Command.class);
        queue.push(mockedCommand);
        queue.push(new HardStopCommand());
        queue.push(new RunCommand());

        // when
        var processor = new Processor(new ProcessableImpl());
        processor.waitThread();

        // then
        assertEquals(1, queue.size());
    }
}
