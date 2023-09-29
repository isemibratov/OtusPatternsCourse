package com.example.hw11.command.operations.area;

import com.example.hw11.command.Command;
import com.example.hw11.command.MacroCommand;
import com.example.hw11.exception.exceptions.CommandException;
import com.example.hw11.game_objects.UObject;

public class DeleteOldAreaThenAddNewOneMacroCommand implements Command {
    private final Command[] commands;

    public DeleteOldAreaThenAddNewOneMacroCommand(
            UObject movableAndAreableObject,
            UObject areaDescriptionObject
    ) {
        this.commands = new Command[] {
                new DeleteOldAreaCommand(movableAndAreableObject, areaDescriptionObject),
                new AddNewAreaCommand(movableAndAreableObject, areaDescriptionObject)
        };
    }

    @Override
    public void execute() {
        try {
            new MacroCommand(commands).execute();
        } catch (Exception ex) {
            throw new CommandException("error when try to delete old area and add new one");
        }
    }
}
