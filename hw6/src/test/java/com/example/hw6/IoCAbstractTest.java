package com.example.hw6;

import com.example.hw6.ioc.adapter.InitAdapterGeneratorCommand;
import com.example.hw6.ioc.scope_based.InitScopeBasedIoCImplCommand;
import com.example.hw6.operations.Fuelable;
import com.example.hw6.operations.Movable;
import com.example.hw6.operations.Rotatable;
import org.junit.jupiter.api.BeforeAll;

public class IoCAbstractTest {
    @BeforeAll
    static void init() {
        new InitScopeBasedIoCImplCommand().execute();
        new InitAdapterGeneratorCommand(Movable.class).execute();
        new InitAdapterGeneratorCommand(Rotatable.class).execute();
        new InitAdapterGeneratorCommand(Fuelable.class).execute();
    }
}
