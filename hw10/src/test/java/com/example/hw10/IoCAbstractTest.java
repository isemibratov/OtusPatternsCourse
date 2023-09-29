package com.example.hw10;

import com.example.hw10.ioc.adapter.InitAdapterGeneratorCommand;
import com.example.hw10.ioc.scope_based.InitScopeBasedIoCImplCommand;
import com.example.hw10.operations.Fuelable;
import com.example.hw10.operations.Movable;
import com.example.hw10.operations.Rotatable;
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
