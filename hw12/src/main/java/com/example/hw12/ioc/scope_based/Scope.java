package com.example.hw12.ioc.scope_based;

@FunctionalInterface
interface Scope {
    Object resolve(String key, Object[] args);
}
