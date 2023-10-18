package com.example.hw12.command.operations.move;

import com.example.hw12.command.Command;
import com.example.hw12.command.MacroCommand;
import com.example.hw12.command.injectable.Injectable;
import com.example.hw12.command.injectable.InjectableCommand;
import com.example.hw12.command.queue.AddElementInQueueCommand;
import com.example.hw12.ioc.IoC;
import com.example.hw12.game_objects.UObject;
import com.example.hw12.operations.Movable;

public class PrepareMovableCommand implements Command {
    private final Movable movableAdapter;
    private final Injectable injectable;
    private final MacroCommand macroCommand;

    public PrepareMovableCommand(UObject movableObject, Command moveCommand) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
        this.injectable = new InjectableCommand();
        var repeatableCommand = new AddElementInQueueCommand((Command) injectable);
        this.macroCommand = new MacroCommand(new Command[] {
                moveCommand, repeatableCommand
        });
    }

    @Override
    public void execute() {
        injectable.inject(macroCommand);
        movableAdapter.setMovement(injectable);
    }
}
