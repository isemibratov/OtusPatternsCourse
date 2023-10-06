package com.example.hw11.game_objects.area;

import com.example.hw11.game_objects.UObject;

import java.util.HashMap;

public class AreaDescriptionObject implements UObject {
    private final HashMap<String, Object> areaDescription;

    public AreaDescriptionObject() {this.areaDescription = new HashMap<>();}

    @Override
    public Object getProperty(String key) {
        return areaDescription.get(key);
    }

    @Override
    public void setProperty(String key, Object newValue) {
        areaDescription.put(key, newValue);
    }
}
