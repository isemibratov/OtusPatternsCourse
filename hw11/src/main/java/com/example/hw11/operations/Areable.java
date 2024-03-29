package com.example.hw11.operations;

import java.util.HashMap;
import java.util.Optional;

public interface Areable {
    Optional<String> getObjId();

    void setObjId(String id);

    Optional<HashMap<String, int[]>> getAreas();

    void setAreas(HashMap<String, int[]> areas);
}
