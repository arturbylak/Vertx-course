package com.bylak.vertx.server;

import org.vertx.java.core.Future;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.platform.Verticle;

/**
 * Created by Artur.Bylak on 2015-05-08.
 */
public class Server extends Verticle {
    @Override
    public void start(final Future<Void> startedResult) {
        HttpServer server = vertx.createHttpServer();
        RouteMatcher routeMatcher = new RouteMatcher();

        routeMatcher.get("/page/:id", new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest request) {
                String id = request.params().get("id");
                System.out.println(
                        "A request has arrived on the server!, id : " + id + " thread " + Thread
                                .currentThread()
                                                                                .getId());
                doSleep();
                request.response().end("Response from thread " + Thread
                        .currentThread().getId());
            }
        });

        server.requestHandler(routeMatcher).listen(8080, "localhost");
    }

    private void doSleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
