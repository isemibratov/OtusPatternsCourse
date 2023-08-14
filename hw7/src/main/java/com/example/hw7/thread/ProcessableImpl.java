package com.example.hw7.thread;

import com.example.hw7.objects.ContextObject;
import com.example.hw7.objects.UObject;

public class ProcessableImpl implements Processable{
    private final UObject context;

    public ProcessableImpl() {
        this.context = ContextObject.getInstance();
    }

    @Override
    public boolean getCanContinue() {
        return (boolean) context.getProperty("canContinue");
    }

    @Override
    public void process() {
         ((Runnable) context.getProperty("process")).run();
    }
}
