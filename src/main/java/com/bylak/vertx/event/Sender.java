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

        System.out.println("Sender > Before send, thread : " + Thread.currentThread()
                                                                            .getId());

        eventBus.publish("test.address", "Message published");
        eventBus.publish("test.address", "Message published 2");
        eventBus.publish("test.address", "Message published 3");

        System.out.println("Sender > After send, thread: " + Thread.currentThread()
                .getId());
    }
}
