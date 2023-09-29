package com.example.hw11.command.operations.area;

import com.example.hw11.command.Command;
import com.example.hw11.command.log.LogErrorCommand;
import com.example.hw11.exception.ExceptionHandler;
import com.example.hw11.exception.exceptions.CommandException;
import com.example.hw11.game_objects.UObject;
import com.example.hw11.game_objects.UObjectWithId;
import com.example.hw11.game_objects.area.AreaDescriptionObject;
import com.example.hw11.game_objects.area.ObjectsInArea;
import com.example.hw11.ioc.IoC;
import com.example.hw11.operations.Areable;
import com.example.hw11.operations.DescribableById;
import com.example.hw11.operations.DescribableByIdAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CheckCollisionsCommand implements Command {
    private final Areable areableAdapter;
    private final DescribableById describableByIdAreasAdapter;

    public CheckCollisionsCommand(
            UObject areableObject,
            UObjectWithId describableByIdAreasObject
    ) {
        this.areableAdapter = IoC.resolve("AreableAdapter", areableObject);
        this.describableByIdAreasAdapter = new DescribableByIdAdapter(describableByIdAreasObject);
    }

    @Override
    public void execute() {
        var areasForObject = areableAdapter
                .getAreas()
                .orElse(new HashMap<>());
        areasForObject.keySet()
                .forEach(
                        this::checkCollisionForArea
                );
    }

    private void checkCollisionForArea(String areaId) {
        try {
            var objId = areableAdapter.getObjId();
            var areaValue = areableAdapter
                    .getAreas()
                    .orElse(new HashMap<>())
                    .get(areaId);
            var areaDescription = (AreaDescriptionObject) describableByIdAreasAdapter.getObjectById(areaId);
            var objectsInArea = (ObjectsInArea) areaDescription.getProperty("ObjectsInArea");
            var objectsInAreaList = objectsInArea
                    .getObjIdsByArea(Arrays.toString(areaValue))
                    .orElse(new ArrayList<>());
            if (objectsInAreaList.size() > 1) {
                throw new CommandException("verdict: collision found for object:" + objId + " in area:" + areaId);
            }
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
