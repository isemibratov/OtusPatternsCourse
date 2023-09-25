package com.example.hw10.ioc.scope_based;

@FunctionalInterface
interface Scope {
    Object resolve(String key, Object[] args);
}
