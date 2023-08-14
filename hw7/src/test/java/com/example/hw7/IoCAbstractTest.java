package com.example.hw7;

import com.example.hw7.ioc.adapter.InitAdapterGeneratorCommand;
import com.example.hw7.ioc.scope_based.InitScopeBasedIoCImplCommand;
import com.example.hw7.operations.Fuelable;
import com.example.hw7.operations.Movable;
import com.example.hw7.operations.Rotatable;
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
