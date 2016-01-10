package mx.ipn.escom.complexsystems.microworld.definition.impl

import mx.ipn.escom.complexsystems.engine.Operations

/**
 * Created by alberto on 9/01/16.
 */
trait MicroWorldAutomata {
    // MicroWorld properties
    int rows = 0;
    int columns = 0;
    boolean start = false;
    Operations operation = new Operations();
    WorldElement[][] world;
    // currentElements and elements lists
    Map worldElements = [:]

    void init(WorldElement[][] world) {
        this.rows = world.length;
        this.columns = world[0].length;
        this.world = world;
    }

    void seedMap(float seed) {
        //
        this.worldElements = this.operation.generateMicroWorldAnimals(seed, this.world);
    }

    void task() {
        int typeSize = this.operation.animalsOrder.size()
        int actionSize = this.operation.actionsOrder.size()
        int elementIndex = Integer.parseInt("${typeSize * Math.random()}")
        int actionIndex = Integer.parseInt("${actionSize * Math.random()}")
        for (int i = elementIndex; i < typeSize + elementIndex; i++) {
            for (int j = actionIndex; j < actionSize + actionIndex; j++) {
                String type = this.operation.animalsOrder[i % typeSize]
                for (element in worldElements."$type") {
                    String action = this.operation.actionsOrder[j % actionSize]
                    element."$action"()
                }
            }
        }
    }
}