package com.example.hw13.game_objects.order;

import com.example.hw13.game_objects.UObject;

import java.util.HashMap;

public class InterpreterableOrderObject implements UObject {
    private final HashMap<String, Object> interpretableOrder;

    public InterpreterableOrderObject(){
        this.interpretableOrder = new HashMap<>();
    }

    @Override
    public Object getProperty(String key) {
        return interpretableOrder.get(key);
    }

    @Override
    public void setProperty(String key, Object newValue) {
        interpretableOrder.put(key, newValue);
    }
}
