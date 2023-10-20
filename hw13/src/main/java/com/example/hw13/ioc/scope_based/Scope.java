package com.example.hw13.ioc.scope_based;

@FunctionalInterface
interface Scope {
    Object resolve(String key, Object[] args);
}
