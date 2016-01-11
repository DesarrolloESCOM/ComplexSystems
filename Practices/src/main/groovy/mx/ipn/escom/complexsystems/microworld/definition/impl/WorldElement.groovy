package mx.ipn.escom.complexsystems.microworld.definition.impl

/**
 * Created by alberto on 9/01/16.
 */
trait WorldElement {
    boolean alive
    int life
    int food
    int water
    int sightRange
    int[] position
    int type
    WorldElement[][] worldCopy

    void drink() {}
    void eat() {}
    Map locationInformation() {}
    void move() {}
    void reproduce() {}
    void decreaseLife(int value) {}
    void decreaseFood(int value) {}
    void decreaseWater(int value) {}
    String toString() {
        return "$type:$life"
    }
}