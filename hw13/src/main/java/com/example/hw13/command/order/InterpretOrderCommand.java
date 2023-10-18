package com.example.hw13.command.order;

import com.example.hw13.command.Command;
import com.example.hw13.command.queue.AddElementInQueueCommand;
import com.example.hw13.exception.ExceptionHandler;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.game_objects.order.InterpreterableOrderObject;
import com.example.hw13.interpreter.ForSubject;
import com.example.hw13.interpreter.Order;
import com.example.hw13.interpreter.WithArgs;
import com.example.hw13.interpreter.WithCommand;
import com.example.hw13.ioc.IoC;
import com.example.hw13.operations.order.Orderable;

public class InterpretOrderCommand implements Command {
    public static final String EMPTY_COMMAND = "EmptyCommand";
    private final Orderable orderableAdapter;
    private final UObject subject;

    public InterpretOrderCommand(UObject order, UObject subject) {
        this.orderableAdapter = IoC.resolve("OrderableAdapter", order);
        this.subject = subject;
    }

    @Override
    public void execute() {
        try {
            var commandArgs = getArgsForCommand();
            var commandName = orderableAdapter.getCommandName()
                    .orElse(EMPTY_COMMAND);
            var command = new Order(
                    new ForSubject(
                            subject,
                            new WithCommand(
                                    commandName,
                                    new WithArgs(commandArgs)))).interpret(new InterpreterableOrderObject());
            new AddElementInQueueCommand(command).execute();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }

    private Object[] getArgsForCommand() {
        var args = new Object[0];
        switch (orderableAdapter.getCommandName().orElse(EMPTY_COMMAND)) {
            case "MoveLinearCommand":
                args = new Object[] {
                        subject
                };
                break;
            case EMPTY_COMMAND:
            default:
                orderableAdapter.setCommandName(EMPTY_COMMAND);
                break;
        }
        return args;
    }
}
