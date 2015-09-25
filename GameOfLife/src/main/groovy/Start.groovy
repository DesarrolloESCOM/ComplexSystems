

import org.vertx.java.platform.impl.cli.Starter

/**
 * Created by alberto on 21/09/15.
 */
class Start {
    static void main(String[] args) {
        // TODO, implement Graphics!!
        Starter.main(["run","groovy:mx.ipn.escom.complexSystems.gameOfLife.web.GameOfLifeVerticle"] as String)
        //"main": "GroovyVerticle.groovy"
    }
}
