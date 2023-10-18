package com.example.hw13.command.ioc;

import static com.example.hw13.ioc.adapter.InitAdapterGeneratorCommand.IOC_REGISTER;

import com.example.hw13.command.Command;
import com.example.hw13.exception.ExceptionHandler;
import com.example.hw13.ioc.IoC;
import com.google.common.reflect.ClassPath;

import java.util.function.Function;

public class RegisterAllCommandsInIocCommand implements Command {

    @Override
    public void execute() {
        try {
            var packageName = "com.example.hw13.command";
            ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(clazz ->
                            clazz.getPackageName()
                                    .startsWith(packageName) &&
                            !clazz.getSimpleName()
                                    .endsWith("Test"))
                    .map(ClassPath.ClassInfo::load)
                    .forEach(
                            commandClass -> IoC.<Command>resolve(
                                            IOC_REGISTER,
                                            commandClass.getSimpleName(),
                                            (Function<Object[], Object>) args -> {
                                                try {
                                                    if (commandClass.getDeclaredFields().length == 0)
                                                        return commandClass.newInstance();
                                                    else
                                                        return commandClass.getConstructors()[0].newInstance(args);
                                                } catch (Exception e) {
                                                    throw new RuntimeException(e);
                                                }
                                            })
                                    .execute()
                    );
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
