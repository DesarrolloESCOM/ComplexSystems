package mx.ipn.escom.complexsystems.microworld.definition.impl

/**
 * Created by alberto on 9/01/16.
 */
trait WorldElement {
    boolean alive = false
    int life = 50
    int food = 50
    int water = 50
    int sightRange
    int[] position
    int type
    WorldElement[][] worldCopy

    void drink() {}
    void eat() {}
    Map locationInformation() {}
    void move() {}
    void reproduce() {}
    void generatePlants() {} //Only used by corpses
    void decreaseLife() {}
    String toString() {
        return "$type"
    }
}