package com.example.hw13;

import com.example.hw13.command.ioc.RegisterAllCommandsInIocCommand;
import com.example.hw13.ioc.adapter.InitAdapterGeneratorCommand;
import com.example.hw13.ioc.scope_based.InitScopeBasedIoCImplCommand;
import com.example.hw13.operations.area.Areable;
import com.example.hw13.operations.area.AreableDescription;
import com.example.hw13.operations.Fuelable;
import com.example.hw13.operations.Movable;
import com.example.hw13.operations.Rotatable;
import com.example.hw13.operations.order.InterpretableOrder;
import com.example.hw13.operations.order.Orderable;
import com.example.hw13.operations.order.OrderableSubject;
import org.junit.jupiter.api.BeforeAll;

public abstract class IoCAbstractTest {
    @BeforeAll
    static void init() {
        new InitScopeBasedIoCImplCommand().execute();
        new RegisterAllCommandsInIocCommand().execute();
        new InitAdapterGeneratorCommand(Movable.class).execute();
        new InitAdapterGeneratorCommand(Rotatable.class).execute();
        new InitAdapterGeneratorCommand(Fuelable.class).execute();
        new InitAdapterGeneratorCommand(Areable.class).execute();
        new InitAdapterGeneratorCommand(AreableDescription.class).execute();
        new InitAdapterGeneratorCommand(Orderable.class).execute();
        new InitAdapterGeneratorCommand(OrderableSubject.class).execute();
        new InitAdapterGeneratorCommand(InterpretableOrder.class).execute();
    }
}
