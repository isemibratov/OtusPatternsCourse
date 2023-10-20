package com.example.hw13.command.order;

import com.example.hw13.command.Command;
import com.example.hw13.exception.ExceptionHandler;
import com.example.hw13.exception.exceptions.CommandException;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.ioc.IoC;
import com.example.hw13.operations.order.Orderable;
import com.example.hw13.operations.order.OrderableSubject;

import java.util.Objects;
import java.util.Optional;

public class CheckOrderCommand implements Command {
    private final Orderable orderableAdapter;
    private final OrderableSubject orderableSubjectAdapter;

    public CheckOrderCommand(UObject order, UObject subject) {
        this.orderableAdapter = IoC.resolve("OrderableAdapter", order);
        this.orderableSubjectAdapter = IoC.resolve("OrderableSubjectAdapter", subject);
    }

    @Override
    public void execute() {
        try {
            Optional.ofNullable(orderableAdapter.getObjId())
                    .ifPresent(
                            o -> Optional.ofNullable(
                                    Objects.equals(
                                            o.orElseThrow(
                                                    () -> new CommandException("Order's object id is null")),
                                            orderableSubjectAdapter.getObjId()
                                                    .orElseThrow(() -> new CommandException("Subject id is null"))
                                    ) ? o.get() : null
                            ).orElseThrow(
                                    () -> new CommandException("Order to object is forbidden: id:" + o.get())));
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
