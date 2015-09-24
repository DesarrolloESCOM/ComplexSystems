package mx.ipn.escom.complexSystems.gameOfLife.engine

/**
 * Created by alberto on 23/09/15.
 */
trait AutomataConstraints {
    int MAX_SIZE = 1000
    short[][] neighborhood = new short[MAX_SIZE][MAX_SIZE]
    short[][] gameOfLife() {
        this.neighborhood
    }
}