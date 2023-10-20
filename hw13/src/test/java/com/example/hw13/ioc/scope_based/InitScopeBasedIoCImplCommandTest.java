package com.example.hw13.ioc.scope_based;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.hw13.ioc.IoC;
import org.junit.jupiter.api.Test;

class InitScopeBasedIoCImplCommandTest {

    @Test
    void shouldInitScopeBasedIoC(){
        // when
        new InitScopeBasedIoCImplCommand().execute();

        // then
        assertNotNull(IoC.resolve("Scopes.Current"));
    }
}