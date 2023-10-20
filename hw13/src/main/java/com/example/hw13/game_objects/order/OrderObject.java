package com.example.hw13.game_objects.order;

import com.example.hw13.game_objects.UObject;

import java.util.HashMap;

public class OrderObject implements UObject {
    private final HashMap<String, Object> order;

    public OrderObject(){
        this.order = new HashMap<>();
    }

    @Override
    public Object getProperty(String key) {
        return order.get(key);
    }

    @Override
    public void setProperty(String key, Object newValue) {
        order.put(key, newValue);
    }
}
