package com.example.hw11.command.operations.area;

import static java.util.Arrays.stream;

import com.example.hw11.command.Command;
import com.example.hw11.game_objects.UObject;
import com.example.hw11.game_objects.area.ObjectsInAreaImpl;
import com.example.hw11.ioc.IoC;
import com.example.hw11.operations.Areable;
import com.example.hw11.operations.AreableDescription;
import com.example.hw11.operations.Movable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AddNewAreaCommand implements Command {
    private final Movable movableAdapter;
    private final Areable areableAdapter;
    private final AreableDescription areableDescriptionAdapter;

    public static final int DEFAULT_AXIS_SIZE = 3;

    public AddNewAreaCommand(
            UObject movableAndAreableObject,
            UObject areaDescriptionObject
    ) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableAndAreableObject);
        this.areableAdapter = IoC.resolve("AreableAdapter", movableAndAreableObject);
        this.areableDescriptionAdapter = IoC.resolve("AreableDescriptionAdapter", areaDescriptionObject);
    }
    @Override
    public void execute() {
        var areaId = areableDescriptionAdapter
                .getAreaId()
                .orElse("");
        var objectsInArea = areableDescriptionAdapter
                .getObjectsInArea()
                .orElse(new ObjectsInAreaImpl());
        var objId = areableAdapter
                .getObjId()
                .orElse("");
        var areasForObject = areableAdapter
                .getAreas()
                .orElse(new HashMap<>());

        var position = movableAdapter.getPosition();
        var axisSize = areableDescriptionAdapter
                .getAxisSize()
                .orElse(DEFAULT_AXIS_SIZE);
        var area = stream(position.orElseThrow(IllegalStateException::new))
                .mapToInt(axis -> (int) Math.ceil(axis / axisSize))
                .toArray();

        var objIdsInArea = objectsInArea
                .getObjIdsByArea(Arrays.toString(area))
                .orElse(new ArrayList<>());
        objIdsInArea.add(objId);
        objectsInArea.updateObjIdsByAreaPosition(Arrays.toString(area), objIdsInArea);
        areasForObject.put(areaId, area);
        areableAdapter.setAreas(areasForObject);
    }
}
