package com.classvar.examples;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExampleEventListener {

    @EventListener
    public void handle(ExampleEntityCreated event) {
        // ... do something ...
    }
}
