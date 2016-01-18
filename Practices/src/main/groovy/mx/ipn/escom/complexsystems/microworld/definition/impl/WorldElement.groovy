package mx.ipn.escom.complexsystems.microworld.definition.impl

import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.elements.Ground

/**
 * Created by alberto on 9/01/16.
 */
trait WorldElement {
    boolean alive = true
    int life
    int sightRange
    List position
    int type
    WorldElement[][] worldCopy
    Map nearInformation = [
            Water    : [exists: false],
            Plant    : [exists: false],
            Carnivore: [exists: false],
            Herbivore: [exists: false],
            Scavenger: [exists: false],
            Corpse   : [exists: false]
    ]
    String canonicalName
    Operations operations = Operations.instance

    void decreaseLife(int value) {
        this.life -= value;
        if (life <= 0) {
            die()
        }
    }

    void increaseLife(int value) {
        if (life < 100) {
            this.life += value;
        }
    }

    void die() {
        this.type = WorldTypes.Corpse.value
        this.alive = false
    }

    void drink() {
        int rows = worldCopy.length
        int columns = worldCopy[0].length
        int partialRow;
        int partialColumn;
        boolean hasDrunk = false;
        for (int row = position[0] - 1; (row <= position[0] + 1) && !hasDrunk; row++) {
            for (int column = position[1] - 1; (column <= position[1] + 1) && !hasDrunk; column++) {
                if (position[1] == column && position[0] == row) {
                    continue;
                }
                partialRow = (row % rows) < 0 ? (row % rows) + rows : (row % rows);
                partialColumn = (column % columns) < 0 ? (column % columns) + columns : (column % columns);
                if (worldCopy[partialRow][partialColumn].type == WorldTypes.Water.value) {
                    increaseLife(10);
                    hasDrunk = true;
                }
            }
        }
    }

    void eat() {}

    void locationInformation() {
        int rows = worldCopy.length
        int columns = worldCopy[0].length
        int partialRow;
        int partialColumn;
        for (int row = position[0] - sightRange; row <= position[0] + sightRange; row++) {
            for (int column = position[1] - sightRange; column <= position[1] + sightRange; column++) {
                if (position[1] == column && position[0] == row) {
                    continue;
                }
                partialRow = (row % rows) < 0 ? (row % rows) + rows : (row % rows);
                partialColumn = (column % columns) < 0 ? (column % columns) + columns : (column % columns);
                switch (worldCopy[partialRow][partialColumn].type) {
                    case 1:
                        nearInformation.Water = [exists: true, position: [partialRow, partialColumn]];
                        break;
                    case 2:
                        nearInformation.Plant = [exists: true, position: [partialRow, partialColumn]];
                        break;
                    case 3:
                        nearInformation.Carnivore = [exists: true, position: [partialRow, partialColumn]];
                        break;
                    case 4:
                        nearInformation.Herbivore = [exists: true, position: [partialRow, partialColumn]];
                        break;
                    case 5:
                        nearInformation.Scavenger = [exists: true, position: [partialRow, partialColumn]];
                        break;
                    case 6:
                        nearInformation.Corpse = [exists: true, position: [partialRow, partialColumn]];
                        break;
                }
            }
        }
    }

    void move() {
        Random random = new Random()
        int movementReasonsSize = operations.movementsReasonMap.size();
        int movementReasonIndex = random.nextInt(movementReasonsSize);
        String reason = operations.movementsReasonMap[movementReasonIndex]
        boolean run = false;

        Map elementsList = [
                "Ground"   : [],
                "Water"    : [],
                "Plant"    : [],
                "Herbivore": [],
                "Carnivore": [],
                "Scavenger": [],
                "Corpse"   : []
        ]

        int rows = worldCopy.length
        int columns = worldCopy[0].length
        int partialRow;
        int partialColumn;
        // Getting all elements from animal's sight
        for (int row = position[0] - sightRange; row <= position[0] + sightRange; row++) {
            for (int column = position[1] - sightRange; column <= position[1] + sightRange; column++) {
                if (position[1] == column && position[0] == row) {
                    continue;
                }
                partialRow = (row % rows) < 0 ? (row % rows) + rows : (row % rows);
                partialColumn = (column % columns) < 0 ? (column % columns) + columns : (column % columns);
                String className = worldCopy[partialRow][partialColumn].class.getSimpleName();
                elementsList."$className".add(worldCopy[partialRow][partialColumn]);
            }
        }
        // should avoid carnivores!
        if (this.type == WorldTypes.Herbivore.value && reason == "Carnivore") {
            run = true;
        }
        if (elementsList.Ground) { // the animal can move just if there's ground near him!
            // considering the random reason movement!
            if (elementsList."$reason".size()) {
                elementsList."$reason".sort {
                    operations.distance(it.position, this.position)
                }
                elementsList.Ground.sort {
                    if (run) {
                        -operations.distance(it.position, elementsList."$reason"[0].position)
                    } else {
                        operations.distance(it.position, elementsList."$reason"[0].position)
                    }
                }
            }
            // creating a new Ground Element
            def prevPosition = this.position
            WorldElement newGround = new Ground()
            newGround.position = this.position
            // assign the nearest/furthest ground position to this object
            this.position = elementsList.Ground[0].position
            worldCopy[this.position[0]][this.position[1]] = this
            // Now assigns to the previous animals position a ground object
            worldCopy[prevPosition[0]][prevPosition[1]] = newGround
            // Decreasing life from this animal after it moved!
            this.decreaseLife(operations.distance(this.position, newGround.position))
        } else {
            this.decreaseLife(1)
        }
    }

    void moveWithInformation() {
        WorldElement neighbor = null;
        Random random = new Random()
        int movementReasonsSize = operations.movementsReasonMap.size();
        int movementReasonIndex = random.nextInt(movementReasonsSize);
        String reason = operations.movementsReasonMap[movementReasonIndex]

        Map elementsList = [
                "Ground"   : [],
                "Water"    : [],
                "Plant"    : [],
                "Herbivore": [],
                "Carnivore": [],
                "Scavenger": [],
                "Corpse"   : []
        ]

        int rows = worldCopy.length
        int columns = worldCopy[0].length
        int partialRow;
        int partialColumn;
        // Getting all elements from animal's sight
        for (int row = position[0] - sightRange; row <= position[0] + sightRange; row++) {
            for (int column = position[1] - sightRange; column <= position[1] + sightRange; column++) {
                if (position[1] == column && position[0] == row) {
                    continue;
                }
                partialRow = (row % rows) < 0 ? (row % rows) + rows : (row % rows);
                partialColumn = (column % columns) < 0 ? (column % columns) + columns : (column % columns);
                String className = worldCopy[partialRow][partialColumn].class.getSimpleName();
                elementsList."$className".add(worldCopy[partialRow][partialColumn]);
            }
        }
        // yay! some fellas near me!
        if (elementsList."${this.class.getSimpleName()}".size()) {
            elementsList."${this.class.getSimpleName()}".sort {
                operations.distance(it.position, this.position)
            }
            neighbor = elementsList."${this.class.getSimpleName()}"[0]
            neighbor.locationInformation()
            Map neighborInformation = neighbor.nearInformation
            if (neighborInformation."$reason".exists) {
                if (elementsList."$reason") {
                    elementsList."$reason".sort {
                        operations.distance(it.position, this.position)
                    }
                    elementsList.Ground.sort {
                        operations.distance(it.position, neighborInformation."$reason".position)
                    }
                }
                if (elementsList.Ground.size()) { // the animal can move just if there's ground near him!
                    // considering the random reason movement!
                    if (elementsList."$reason") {
                        elementsList."$reason".sort {
                            operations.distance(it.position, this.position)
                        }
                        elementsList.Ground.sort {
                            operations.distance(it.position, neighbor.position)
                        }
                    }
                    // creating a new Ground Element
                    def prevPosition = this.position
                    WorldElement newGround = new Ground()
                    newGround.position = this.position
                    newGround.worldCopy = this.worldCopy
                    // assign the nearest/furthest ground position to this object
                    this.position = elementsList.Ground[0].position
                    worldCopy[this.position[0]][this.position[1]] = this
                    // Now assigns to the previous animals position a ground object
                    worldCopy[prevPosition[0]][prevPosition[1]] = newGround
                    // Decreasing life from this animal after it moved!
                    this.decreaseLife(operations.distance(this.position, newGround.position))
                }
            } else {
                this.decreaseLife(1)
            }
        } else { // couldn't move :(
            this.decreaseLife(1)
        }
    }

    void reproduce() {
        int rows = worldCopy.length
        int columns = worldCopy[0].length
        int partialRow;
        int partialColumn;
        boolean hasBorn = false;
        if (life > 50) {
            for (int row = position[0] - 1; (row <= position[0] + 1) && !hasBorn; row++) {
                for (int column = position[1] - 1; (column <= position[1] + 1) && !hasBorn; column++) {
                    if (position[1] == column && position[0] == row) {
                        continue;
                    }
                    partialRow = (row % rows) < 0 ? (row % rows) + rows : (row % rows);
                    partialColumn = (column % columns) < 0 ? (column % columns) + columns : (column % columns);
                    if (worldCopy[partialRow][partialColumn].type == WorldTypes.Ground.value) {
                        WorldElement element = Class.forName(canonicalName).newInstance()
                        element.position = [partialRow, partialColumn]
                        element.worldCopy = this.worldCopy
                        worldCopy[partialRow][partialColumn] = element
                        hasBorn = true;
                        decreaseLife((int) (this.life / 2));
                        continue;
                    }
                }
            }
        }
    }

    String toString() {
        return "[$position]|$type|$life"
    }
}