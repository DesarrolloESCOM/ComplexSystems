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
        // Loading index
        routeMatcher.get("/") { request ->
            request.response.sendFile("web/index.html")
        }
        // Loading css resources
        routeMatcher.get("/css/:file") { request ->
            request.response.sendFile("web/css/${request.params['file']}");
        }
        // Loading js resources
        routeMatcher.get("/js/:file") { request ->
            request.response.sendFile("web/js/${request.params['file']}")
        }
        //eventBus.registerHandler("start")
        //eventBus.registerHandler("stop")
        //eventBus.registerHandler("resize")
        server.requestHandler(routeMatcher.asClosure()).listen(7777, "localhost");
    }
}
