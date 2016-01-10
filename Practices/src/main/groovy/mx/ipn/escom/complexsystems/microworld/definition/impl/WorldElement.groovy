package mx.ipn.escom.complexsystems.microworld.definition.impl

/**
 * Created by alberto on 9/01/16.
 */
interface WorldElement {
    boolean alive = false
    int life
    int sightRange
    int[] position
    int type
    WorldElement[][] worldCopy

    void drink()
    void eat()
    Map locationInformation()
    void move()
    void reproduce()
    void generatePlants() //Only used by corpses
}