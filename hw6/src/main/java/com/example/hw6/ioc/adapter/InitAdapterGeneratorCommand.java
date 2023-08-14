package com.example.hw6.ioc.adapter;

import com.example.hw6.command.Command;
import com.example.hw6.command.operations.StopCommand;
import com.example.hw6.command.operations.UpdateObjectPropertyCommand;
import com.example.hw6.exception.exceptions.CommandException;
import com.example.hw6.ioc.IoC;
import com.example.hw6.objects.UObject;
import com.example.hw6.operations.Movable;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InitAdapterGeneratorCommand implements Command {
    public static final String IOC_REGISTER = "IoC.Register";
    private final Class<?> adapterInterface;

    public InitAdapterGeneratorCommand(Class<?> adapterInterface) {
        this.adapterInterface = adapterInterface;
    }

    @Override
    public void execute() {
        try {
            var className = String.format("%sAdapter", adapterInterface.getSimpleName());
            var methods = adapterInterface.getMethods();
            var source = String.format(
                    "package com.example.hw6.operations; public class %s implements %s { %s %s %s }",
                    className,
                    adapterInterface.getName(),
                    "private final com.example.hw6.objects.UObject uObject;",
                    getConstructor(className),
                    getInterfaceMethods(methods));

            // Save source in .java file.
            File root = Files.createTempDirectory("java").toFile();
            File sourceFile = new File(
                    root,
                    String.format("com/example/hw6/operations/%s.java", className));
            sourceFile.getParentFile().mkdirs();
            Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));

            // Compile source file.
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, null, null, sourceFile.getPath());

            // Load and instantiate compiled class.
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{root.toURI().toURL()});
            Class<?> cls = Class.forName(
                    String.format("com.example.hw6.operations.%s", className),
                    true,
                    classLoader);
            Constructor<?> constructor = cls.getConstructor(UObject.class);
            IoC.<Command>resolve(
                            IOC_REGISTER,
                            className,
                            (Function<Object[], Object>) args -> {
                                try {
                                    return constructor.newInstance(args[0]);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            })
                    .execute();
        } catch (Exception e) {
            throw new CommandException("Error while generate adapter");
        }
    }

    private String getConstructor(String className) {
        return String.format(
                "public %s(com.example.hw6.objects.UObject uObject) { this.uObject = uObject;}",
                className);
    }

    private String getInterfaceMethods(Method[] methods) {
        registerIoCsResolvesForMethods();
        return String.format(
                "%s %s %s",
                prepareGetMethods(methods),
                prepareSetMethods(methods),
                prepareFinishMethod(methods));
    }

    private void registerIoCsResolvesForMethods() {
        IoC.<Command>resolve(
                        IOC_REGISTER,
                        String.format(
                                "%s.get",
                                adapterInterface.getSimpleName()
                        ),
                        (Function<Object[], Object>) args -> ((UObject) args[1]).getProperty((String) args[0]))
                .execute();
        IoC.<Command>resolve(
                        IOC_REGISTER,
                        String.format(
                                "%s.set",
                                adapterInterface.getSimpleName()
                        ),
                        (Function<Object[], Object>) args -> new UpdateObjectPropertyCommand((UObject) args[1], (String) args[0], args[2]))
                .execute();
        IoC.<Command>resolve(
                        IOC_REGISTER,
                         "MovableAdapter.finish",
                        (Function<Object[], Object>) args -> new StopCommand((Movable) args[0]))
                .execute();
    }

    private String prepareGetMethods(Method[] methods) {
        return Arrays.stream(methods)
                .filter(it -> it.getName().startsWith("get"))
                .map(this::prepareGetMethod)
                .collect(Collectors.joining(" "));
    }

    private String prepareGetMethod(Method method) {
        var propertyName = method.getName().replace("get", "");
        var methodReturnType = method.getGenericReturnType();
        return String.format(
                "@Override public %s %s() { %s }",
                methodReturnType,
                method.getName(),
                prepareGetMethodBody(propertyName, methodReturnType));
    }

    private String prepareGetMethodBody(String propertyName, Type methodReturnType) {
        return String.format(
                "return java.util.Optional.ofNullable(com.example.hw6.ioc.IoC.<%s>resolve(\"%s.get\", \"%s\", uObject));",
                methodReturnType
                        .getTypeName()
                        .replace("java.util.Optional<", "")
                        .replace(">", ""),
                adapterInterface.getSimpleName(),
                propertyName);
    }

    private String prepareSetMethods(Method[] methods) {
        return Arrays.stream(methods)
                .filter(it -> it.getName().startsWith("set"))
                .map(this::prepareSetMethod)
                .collect(Collectors.joining(" "));
    }

    private String prepareSetMethod(Method method) {
        var param = method.getParameters()[0];
        var propertyName = method.getName().replace("set", "");
        return String.format(
                "@Override public %s %s(%s) { %s }",
                method.getReturnType(),
                method.getName(),
                param.toString(),
                prepareSetMethodBody(propertyName, param));
    }

    private String prepareSetMethodBody(String propertyName, Parameter param) {
        return String.format(
                "com.example.hw6.ioc.IoC.<com.example.hw6.command.Command>resolve(\"%s.set\", \"%s\", uObject, %s).execute();",
                adapterInterface.getSimpleName(),
                propertyName,
                param.getName());
    }

    private String prepareFinishMethod(Method[] methods) {
        return Arrays.stream(methods)
                .filter(it ->
                        adapterInterface.isAssignableFrom(Movable.class) &&
                                it.getName().equals("finish"))
                .map(it -> "@Override public void finish() { new com.example.hw6.command.operations.StopCommand(this).execute(); }")
                .collect(Collectors.joining(" "));
    }
}
