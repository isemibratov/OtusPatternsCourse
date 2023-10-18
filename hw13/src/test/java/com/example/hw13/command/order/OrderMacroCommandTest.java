package com.example.hw13.command.order;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.hw13.IoCAbstractTest;
import com.example.hw13.command.Command;
import com.example.hw13.command.EmptyCommand;
import com.example.hw13.command.operations.move.MoveLinearCommand;
import com.example.hw13.exception.exceptions.CommandException;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.game_objects.order.OrderObject;
import com.example.hw13.game_objects.spaceship.SpaceshipObject;
import com.example.hw13.ioc.IoC;
import com.example.hw13.operations.order.Orderable;
import com.example.hw13.queue.LinkedListCommandQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderMacroCommandTest extends IoCAbstractTest {
    private UObject object;
    private UObject order;

    @BeforeEach
    void beforeTest(){
        order = new OrderObject();
    }

    @Test
    void shouldAddEmptyCommandInQueueIfOrderWithoutCommandName() {
        // given
        var id = "idVal";
        object = new SpaceshipObject();
        order.setProperty("ObjId", id);
        object.setProperty("ObjId", id);
        var command = IoC.<Command>resolve("OrderMacroCommand", order, object);

        // when
        command.execute();

        // then
        var queue = LinkedListCommandQueue.getInstance();
        assertAll(
                () -> assertEquals(1, queue.size()),
                () -> assertEquals(EmptyCommand.class.getName(), queue.pop().getClass().getName())
        );
    }

    @Test
    void shouldAddEmptyCommandInQueueIfOrderCommandNameIsEmptyCommand() {
        // given
        var id = "idVal";
        object = new SpaceshipObject();
        var orderableAdapter = IoC.<Orderable>resolve("OrderableAdapter", order);
        orderableAdapter.setObjId(id);
        orderableAdapter.setCommandName("EmptyCommand");
        object.setProperty("ObjId", id);
        var command = IoC.<Command>resolve("OrderMacroCommand", order, object);

        // when
        command.execute();

        // then
        var queue = LinkedListCommandQueue.getInstance();
        assertAll(
                () -> assertEquals(1, queue.size()),
                () -> assertEquals(EmptyCommand.class.getName(), queue.pop().getClass().getName())
        );
    }

    @Test
    void shouldAddEmptyCommandInQueueIfOrderCommandNameIsUnknown() {
        // given
        var id = "idVal";
        object = new SpaceshipObject();
        var orderableAdapter = IoC.<Orderable>resolve("OrderableAdapter", order);
        orderableAdapter.setObjId(id);
        orderableAdapter.setCommandName("Unknown");
        object.setProperty("ObjId", id);
        var command = IoC.<Command>resolve("OrderMacroCommand", order, object);

        // when
        command.execute();

        // then
        var queue = LinkedListCommandQueue.getInstance();
        assertAll(
                () -> assertEquals(1, queue.size()),
                () -> assertEquals(EmptyCommand.class.getName(), queue.pop().getClass().getName())
        );
    }

    @Test
    void shouldAddMoveLinearCommandInQueue() {
        // given
        var id = "idVal";
        object = new SpaceshipObject();
        var orderableAdapter = IoC.<Orderable>resolve("OrderableAdapter", order);
        orderableAdapter.setObjId(id);
        orderableAdapter.setCommandName("MoveLinearCommand");
        object.setProperty("ObjId", id);
        object.setProperty("Position", new double[]{12, 5});
        object.setProperty("VelocityVector", new double[]{-7, 3});
        var command = IoC.<Command>resolve("OrderMacroCommand", order, object);

        // when
        command.execute();

        // then
        var queue = LinkedListCommandQueue.getInstance();
        assertAll(
                () -> assertEquals(1, queue.size()),
                () -> assertEquals(MoveLinearCommand.class.getName(), queue.pop().getClass().getName())
        );
    }

    @Test
    void shouldThrowCommandExceptionWhenObjIdIsNullInOrder() {
        // given
        object = new SpaceshipObject();
        order.setProperty("ObjId", null);
        var command = new OrderMacroCommand(order, object);

        // when+then
        assertThrows(
                CommandException.class,
                command::execute);
    }

    @Test
    void shouldThrowCommandExceptionWhenObjIdIsNullInObject() {
        // given
        var id = "idVal";
        object = new SpaceshipObject();
        order.setProperty("ObjId", id);
        object.setProperty("ObjId", null);
        var command = new OrderMacroCommand(order, object);

        // when+then
        assertThrows(
                CommandException.class,
                command::execute);
    }

    @Test
    void shouldThrowCommandExceptionWhenObjIdsIsNotEquals() {
        // given
        var id = "idVal";
        object = new SpaceshipObject();
        order.setProperty("ObjId", id);
        object.setProperty("ObjId", id+"Wrong");
        var command = new OrderMacroCommand(order, object);

        // when+then
        assertThrows(
                CommandException.class,
                command::execute);
    }
}