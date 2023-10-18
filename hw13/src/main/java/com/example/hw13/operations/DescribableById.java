package com.example.hw13.operations;

import com.example.hw13.game_objects.UObject;

import java.util.Set;

public interface DescribableById {
    UObject getObjectById(String id);

    void setObjectById(String id, UObject object);

    Set<String> getAllIds();
}
