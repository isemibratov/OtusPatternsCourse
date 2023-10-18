package com.example.hw13.command.operations.area;

import com.example.hw13.command.Command;
import com.example.hw13.exception.exceptions.CommandException;
import com.example.hw13.game_objects.UObject;
import com.example.hw13.game_objects.UObjectWithId;
import com.example.hw13.game_objects.area.AreaDescriptionObject;
import com.example.hw13.operations.DescribableById;
import com.example.hw13.operations.DescribableByIdAdapter;

public class UpdateAreasForObjectCommand implements Command {

    private final UObject movableAndAreableObject;
    private final DescribableById describableByIdAreasAdapter;

    public UpdateAreasForObjectCommand(
            UObject movableAndAreableObject,
            UObjectWithId describableByIdAreasObject
    ) {
        this.movableAndAreableObject = movableAndAreableObject;
        this.describableByIdAreasAdapter = new DescribableByIdAdapter(describableByIdAreasObject);
    }

    @Override
    public void execute() {
        try {
            describableByIdAreasAdapter.getAllIds()
                    .forEach(this::processArea);
        } catch (Exception ex) {
            throw new CommandException("error when perform command");
        }
    }

    private void processArea(String id) {
        var areaDescriptionObject = (AreaDescriptionObject) describableByIdAreasAdapter.getObjectById(id);
        new DeleteOldAreaThenAddNewOneMacroCommand(
                movableAndAreableObject,
                areaDescriptionObject).execute();
    }
}
