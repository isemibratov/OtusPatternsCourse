package com.example.hw11.operations;

import com.example.hw11.game_objects.UObject;

import java.util.Set;

public interface DescribableById {
    UObject getObjectById(String id);

    void setObjectById(String id, UObject object);

    Set<String> getAllIds();
}
