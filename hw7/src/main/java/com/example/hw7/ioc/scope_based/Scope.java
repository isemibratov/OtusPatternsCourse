package com.example.hw7.ioc.scope_based;

@FunctionalInterface
interface Scope {
    Object resolve(String key, Object[] args);
}
