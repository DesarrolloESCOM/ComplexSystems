package mx.ipn.escom.complexSystems.gameOfLife.web

import groovy.json.JsonOutput
import org.vertx.groovy.core.http.RouteMatcher
import org.vertx.groovy.platform.Verticle

/**
 * Created by alberto on 24/09/15.
 */
class GameOfLifeVerticle extends Verticle{
    def start() {
        def eventBus = vertx.eventBus
        def server = vertx.createHttpServer()
        def routeMatcher = new RouteMatcher();
        routeMatcher.get("/helloWorld") { request ->
            request.response.end("Hello World");
        }
        //Loading index
        routeMatcher.get("/") { request ->
            def webroot = System.getProperty("user.dir") + File.separator + "web"
            request.response.sendFile webroot + File.separator + "index.html"
        }
        //Loading jquery
        //Loading SockJS
        server.requestHandler(routeMatcher.asClosure()).listen(7777, "localhost");
    }
}
