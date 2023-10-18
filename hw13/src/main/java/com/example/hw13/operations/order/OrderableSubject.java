package com.example.hw13.operations.order;

import java.util.Optional;

public interface OrderableSubject {
    Optional<String> getObjId();

    void setObjId(String id);
}
