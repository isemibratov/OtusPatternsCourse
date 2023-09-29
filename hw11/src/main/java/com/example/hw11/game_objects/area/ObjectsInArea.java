package com.example.hw11.game_objects.area;

import java.util.List;
import java.util.Optional;

public interface ObjectsInArea {
    Optional<List<String>> getObjIdsByArea(String areaPosition);

    void updateObjIdsByAreaPosition(String areaPosition, List<String> objIdList);
}
