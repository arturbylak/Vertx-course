package com.bylak.vertx.event;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class Receiver extends Verticle {
    @Override
    public void start() {
        final Vertx vertx = getVertx();
        final EventBus eventBus = vertx.eventBus();

        eventBus.registerHandler("test.address", this::handle);
    }

    private void handle(final Message<String> message) {
        System.out.println("Receiver > " +message.body());

        message.reply("This is a reply");
    }
}
