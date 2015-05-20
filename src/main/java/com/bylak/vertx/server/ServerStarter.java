package com.bylak.vertx.server;

import java.util.function.Consumer;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Future;
import org.vertx.java.platform.Verticle;

/**
 * Created by Artur.Bylak on 2015-05-08.
 */
public class ServerStarter extends Verticle {
    @Override
    public void start(final Future<Void> startedResult) {
        // For example - deploy some other verticle
        System.out.println("Starter  thread : " + Thread.currentThread().getId());
        deployVerticle("Server.java", 5, null);
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
