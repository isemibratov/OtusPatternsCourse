package com.example.hw12.operations.area;

import com.example.hw12.game_objects.area.ObjectsInArea;

import java.util.Optional;

public interface AreableDescription {
    Optional<Integer> getAxisSize();

    void setAxisSize(int newValue);

    Optional<String> getAreaId();

    void setAreaId(String newValue);

    Optional<ObjectsInArea> getObjectsInArea();

    void setObjectsInArea(ObjectsInArea newValue);
}
