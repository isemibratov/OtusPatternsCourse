package com.example.hw12.command.operations.area;

import com.example.hw12.command.Command;
import com.example.hw12.game_objects.UObject;
import com.example.hw12.game_objects.area.ObjectsInAreaImpl;
import com.example.hw12.ioc.IoC;
import com.example.hw12.operations.area.Areable;
import com.example.hw12.operations.area.AreableDescription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DeleteOldAreaCommand implements Command {
    private final Areable areableAdapter;
    private final AreableDescription areableDescriptionAdapter;

    public DeleteOldAreaCommand(
            UObject areableObject,
            UObject areaDescriptionObject
    ) {
        this.areableAdapter = IoC.resolve("AreableAdapter", areableObject);
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
        var spaceshipId = areableAdapter
                .getObjId()
                .orElse("");
        var areasForObject = areableAdapter
                .getAreas()
                .orElse(new HashMap<>());

        var area = areasForObject.get(areaId);
        var objIdsForArea = objectsInArea
                .getObjIdsByArea(Arrays.toString(area))
                .orElse(new ArrayList<>());
        objIdsForArea.remove(spaceshipId);
        objectsInArea.updateObjIdsByAreaPosition(Arrays.toString(area), objIdsForArea);
        areableAdapter.setAreas(areasForObject);
    }
}
