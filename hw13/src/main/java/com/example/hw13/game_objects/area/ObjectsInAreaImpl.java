package com.example.hw13.game_objects.area;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ObjectsInAreaImpl implements ObjectsInArea {
    private final HashMap<String, List<String>> area;

    public ObjectsInAreaImpl() {this.area = new HashMap<>();}

    public ObjectsInAreaImpl(HashMap<String, List<String>> area) {this.area = area;}

    @Override
    public Optional<List<String>> getObjIdsByArea(String areaPosition) {
        return Optional.ofNullable(area.get(areaPosition));
    }

    @Override
    public void updateObjIdsByAreaPosition(String areaPosition, List<String> objIdList) {
        area.put(areaPosition, objIdList);
    }
}
