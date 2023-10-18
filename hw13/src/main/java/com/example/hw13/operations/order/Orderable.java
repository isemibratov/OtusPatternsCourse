package com.example.hw13.operations.order;

import java.util.Optional;

public interface Orderable {
    Optional<String> getObjId();

    void setObjId(String newValue);

    Optional<String> getCommandName();

    void setCommandName(String newValue);

    Optional<String> getInitialVelocity();

    void setInitialVelocity(String newValue);
}

