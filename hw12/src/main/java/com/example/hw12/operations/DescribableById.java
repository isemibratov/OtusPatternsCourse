package com.example.hw12.operations;

import com.example.hw12.game_objects.UObject;

import java.util.Set;

public interface DescribableById {
    UObject getObjectById(String id);

    void setObjectById(String id, UObject object);

    Set<String> getAllIds();
}
