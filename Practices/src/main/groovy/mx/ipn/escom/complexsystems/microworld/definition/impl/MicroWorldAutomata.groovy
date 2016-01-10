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
    def typeOrder = [0: "carnivores", 1: "herbivores", 2: "scavengers", 3: "corpses", 4: "plants", 5: "ground"]
    def actionsOrder = [0: "drink", 1: "eat", 2: "locationInformation", 3: "move", 4: "reproduce", 5: "generatePlants"]
    // Statistics
    int currentCarnivore = 0;
    int currentHerbivore = 0;
    int currentScavenger = 0;
    int currentCorpses = 0;
    int currentPlants = 0;
    int currentGround = 0;
    // Current Animals, Corpses, Plants and Ground
    // New Animals, Corpses, Plants and Ground
    Map worldElements = [:]

    void init(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.world = world != null ? world : this.operation.generateRandomWorld(this.rows, this.columns);
    }

    void init(WorldElement[][] neighborhood) {
        this.rows = neighborhood.length;
        this.columns = neighborhood[0].length;
        this.world = neighborhood;
    }

    void init(float seed, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        //
        Map worldComponents = this.operation.generateMicroWorldAnimals(seed, this.rows, this.columns);
        this.world = worldComponents.world
        //
        currentCarnivore = worldComponents.currentCarnivore
        currentCorpses = worldComponents.currentCorpses
        currentGround = worldComponents.currentGround
        currentHerbivore = worldComponents.currentHerbivore
        currentPlants = worldComponents.currentPlants
        currentScavenger = worldComponents.currentScavenger
        //
        worldElements.carnivores = worldComponents.carnivores
        worldElements.herbivores = worldComponents.herbivors
        worldElements.scavengers = worldComponents.scavengers
        worldElements.corpses = worldComponents.corpses
        worldElements.plants = worldComponents.plants
        worldElements.ground = worldComponents.ground

    }

    void task() {
        int typeSize = typeOrder.size()
        int actionSize = actionsOrder.size()
        int elementIndex = Integer.parseInt("${typeSize * Math.random()}")
        int actionIndex = Integer.parseInt("${actionSize * Math.random()}")
        for (int i = elementIndex; i < typeSize + elementIndex; i++) {
            for (int j = actionIndex; j < actionSize + actionIndex; j++) {
                String type = typeOrder[i % typeSize]
                for (element in worldElements."$type") {
                    String action = actionsOrder[j % actionSize]
                    element."$action"()
                }
            }
        }
    }
}