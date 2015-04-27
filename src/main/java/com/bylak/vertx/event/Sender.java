package com.bylak.vertx.event;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class Sender extends Verticle {
    @Override
    public void start() {
        final Vertx vertx = getVertx();
        final EventBus eventBus = vertx.eventBus();

        System.out.println("Sender before send");

        eventBus.send("test.address", "Message published", (Message<String> response) ->
                System.out.println(response.body()));

        System.out.println("Sender after send");
    }
}
