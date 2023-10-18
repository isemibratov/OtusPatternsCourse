package com.example.hw12.game_objects.area;

import com.example.hw12.game_objects.UObjectWithId;

import java.util.HashMap;
import java.util.Set;

public class DescribableByIdAreasObject implements UObjectWithId {
    private static DescribableByIdAreasObject instance;
    private final HashMap<String, Object> areas;

    public DescribableByIdAreasObject(){
        this.areas = new HashMap<>();
    }

    public static synchronized DescribableByIdAreasObject getInstance() {
        if (instance == null) {instance = new DescribableByIdAreasObject();}
        return instance;
    }

    @Override
    public Object getProperty(String key) {
        return areas.getOrDefault(key, new AreaDescriptionObject());
    }

    @Override
    public void setProperty(String key, Object areaDescription) {
        areas.put(key, areaDescription);
    }

    @Override
    public Set<String> getAllIds() {
        return areas.keySet();
    }
}
