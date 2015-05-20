package com.bylak.vertx.server;

import java.util.Map;
import org.vertx.java.core.Future;
import org.vertx.java.core.Handler;
import org.vertx.java.core.VoidHandler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;

/**
 * Created by Artur.Bylak on 2015-05-08.
 */
public class Server extends Verticle {
    @Override
    public void start(final Future<Void> startedResult) {
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
            public void handle(final HttpServerRequest req) {
                if (req.uri().equals("/")) {
                    // Serve the index page
                    req.response().sendFile("index.html");
                } else if (req.uri().startsWith("/form")) {
                    req.response().setChunked(true);
                    req.expectMultiPart(true);
                    req.endHandler(new VoidHandler() {
                        protected void handle() {
                            for (Map.Entry<String, String> entry : req.formAttributes()) {
                                req.response()
                                   .write("Got attr " + entry.getKey() + " : " + entry.getValue()
                                           + "\n");
                            }
                            req.response().end();
                        }
                    });
                } else {
                    req.response().setStatusCode(404);
                    req.response().end();
                }
            }
        }).listen(8080);
    }

}
