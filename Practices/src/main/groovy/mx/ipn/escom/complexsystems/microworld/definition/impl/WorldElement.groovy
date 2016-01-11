package mx.ipn.escom.complexsystems.microworld.definition.impl

import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.elements.Herbivore

/**
 * Created by alberto on 9/01/16.
 */
trait WorldElement {
    boolean alive = true
    int life
    int sightRange
    int[] position
    int type
    WorldElement[][] worldCopy
    Map nearInformation = [
            Water    : false,
            Plant    : false,
            Carnivore: false,
            Herbivore: false,
            Scavenger: false,
            Corpse   : false
    ]
    String canonicalName

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
        boolean hasDrink = false;
        for (int row = position[0] - 1; (row <= position[0] + 1) && !hasDrink; row++) {
            for (int column = position[1] - 1; (column <= position[1] + 1) && !hasDrink; column++) {
                if (position[1] == column && position[0] == row) {
                    continue;
                }
                partialRow = (row % rows) < 0 ? (row % rows) + rows : (row % rows);
                partialColumn = (column % columns) < 0 ? (column % columns) + columns : (column % columns);
                if (worldCopy[partialRow][partialColumn].type == WorldTypes.Water.value) {
                    increaseLife(5);
                    hasDrink = true;
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
                        nearInformation.Water = true;
                        break;
                    case 2:
                        nearInformation.Plant = true;
                        break;
                    case 3:
                        nearInformation.Carnivore = true;
                        break;
                    case 4:
                        nearInformation.Herbivore = true;
                        break;
                    case 5:
                        nearInformation.Scavenger = true;
                        break;
                    case 6:
                        nearInformation.Corpse = true;
                        break;
                }
            }
        }
    }

    void move() {}

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
                        worldCopy[partialRow][partialColumn] = element
                        hasBorn = true;
                        decreaseLife(50);
                    }
                }
            }
        }
    }

    String toString() {
        return "$type:$life"
    }
}