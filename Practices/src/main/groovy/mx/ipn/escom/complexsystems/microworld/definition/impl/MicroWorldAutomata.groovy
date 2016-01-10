package mx.ipn.escom.complexsystems.microworld.definition.impl

import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement

/**
 * Created by alberto on 9/01/16.
 */
trait MicroWorldAutomata {
    WorldElement[][] neighborhood;
    int rows = 0;
    int columns = 0;
    // Statistics
    int currentCarnivore = 0;
    int currentHerbivore = 0;
    int currentScavenger = 0;
    int currentCorpses = 0;
    int currentPlants = 0;
    int currentGround = 0;
    // Animals and Corpses
    ArrayList<int[]> newCarnivore = new ArrayList<>();
    ArrayList<int[]> newHerbivore = new ArrayList<>();
    ArrayList<int[]> newScavenger = new ArrayList<>();
    ArrayList<int[]> newCorpses = new ArrayList<>();
    // Plants and Ground
    ArrayList<int[]> newPlants = new ArrayList<>();
    ArrayList<int[]> newGround = new ArrayList<>();

    boolean start = false;
    Operations operation = new Operations();

    void init(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.neighborhood = neighborhood != null ? neighborhood : this.operation.generateRandomWorld(this.rows, this.columns);
    }

    void init(WorldElement[][] neighborhood) {
        this.rows = neighborhood.length;
        this.columns = neighborhood[0].length;
        this.neighborhood = neighborhood;
    }

    void init(float seed, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        //
        Map worldComponents = this.operation.generateMicroWorldAnimals(seed, this.rows, this.columns);
        this.neighborhood = worldComponents.world
        //
        currentCarnivore = worldComponents.currentCarnivore
        currentCorpses = worldComponents.currentCorpses
        currentGround = worldComponents.currentGround
        currentHerbivore = worldComponents.currentHerbivore
        currentPlants = worldComponents.currentPlants
        currentScavenger = worldComponents.currentScavenger
        //
        newCarnivore = worldComponents.newCarnivore
        newHerbivore = worldComponents.newHerbivore
        newScavenger = worldComponents.newScavenger
        newCorpses = worldComponents.newCorpses
        newPlants = worldComponents.newPlants
        newGround = worldComponents.newGround

    }

    void task() {}
}