package com.example.hw12;

import com.example.hw12.ioc.adapter.InitAdapterGeneratorCommand;
import com.example.hw12.ioc.scope_based.InitScopeBasedIoCImplCommand;
import com.example.hw12.operations.area.Areable;
import com.example.hw12.operations.area.AreableDescription;
import com.example.hw12.operations.Fuelable;
import com.example.hw12.operations.Movable;
import com.example.hw12.operations.Rotatable;
import org.junit.jupiter.api.BeforeAll;

public class IoCAbstractTest {
    @BeforeAll
    static void init() {
        new InitScopeBasedIoCImplCommand().execute();
        new InitAdapterGeneratorCommand(Movable.class).execute();
        new InitAdapterGeneratorCommand(Rotatable.class).execute();
        new InitAdapterGeneratorCommand(Fuelable.class).execute();
        new InitAdapterGeneratorCommand(Areable.class).execute();
        new InitAdapterGeneratorCommand(AreableDescription.class).execute();
    }
}
