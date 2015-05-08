package com.bylak.vertx.event;

import java.util.function.Consumer;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Future;
import org.vertx.java.platform.Verticle;

public class Starter extends Verticle {
    @Override
    public void start(final Future<Void> startedResult) {
        // For example - deploy some other verticle
        System.out.println("Starter  thread : "  + Thread.currentThread().getId());
        deployVerticle("Receiver.java", 1, (Void) -> deployVerticle("Sender.java", 1, null));
    }

    private void deployVerticle(final String path, final int instances, final Consumer<Void>
            consumer) {
        container.deployVerticle(path, instances, new AsyncResultHandler<String>() {
            public void handle(AsyncResult<String> deployResult) {
                if (deployResult.succeeded() && consumer != null) {
                    consumer.accept(null);
                }
            }
        });
    }
}
