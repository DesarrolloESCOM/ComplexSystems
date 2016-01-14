package mx.ipn.escom.complexsystems.microworld.definition.impl

import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.elements.Corpse

/**
 * Created by alberto on 9/01/16.
 */
trait MicroWorldAutomata {
    // Automata basic property
    int generation = 0;
    // MicroWorld properties
    int rows = 0;
    int columns = 0;
    boolean start = false;
    Operations operation = Operations.instance
    WorldElement[][] world;


    void init(WorldElement[][] world) {
        this.rows = world.length;
        this.columns = world[0].length;
        this.world = world;
    }

    void task() {
    }
}