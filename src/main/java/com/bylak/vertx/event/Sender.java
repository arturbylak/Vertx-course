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

        eventBus.send("test.address", "Message published", (Message<String> response) ->
                print(response));
        eventBus.send("test.address", "Message published 2", (Message<String> response) ->
                print(response));
        eventBus.send("test.address", "Message published 3", (Message<String> response) ->
                print(response));

        System.out.println("Sender > After send, thread: " + Thread.currentThread()
                .getId());
    }

    private void print(Message<String> response) {
        System.out.println("Sender > " + response.body() + " thread: " + Thread
                .currentThread()
                .getId());
    }
}
