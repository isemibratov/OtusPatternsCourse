package com.example.hw2.objects;

import java.util.HashMap;

public class SpaceshipObject implements UObject {
    private final HashMap<String, Object> spaceship;

    public SpaceshipObject(){
        this.spaceship = new HashMap<>();
    }

    @Override
    public Object getProperty(String key) {
        return spaceship.get(key);
    }

    @Override
    public void setProperty(String key, Object newValue) {
        spaceship.put(key, newValue);
    }
}
