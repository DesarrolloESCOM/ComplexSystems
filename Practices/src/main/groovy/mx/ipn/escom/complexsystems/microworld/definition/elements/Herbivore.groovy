package mx.ipn.escom.complexsystems.microworld.definition.elements

import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 9/01/16.
 */
class Herbivore implements WorldElement {
    public Herbivore() {
        this.sightRange = 2;
        this.life = 50;
        this.type = WorldTypes.Herbivore.value;
        this.canonicalName = "${Operations.elementsPackageName}.${this.class.getSimpleName()}"
    }

    void eat() {
        int rows = worldCopy.length
        int columns = worldCopy[0].length
        int partialRow;
        int partialColumn;
        boolean hasEaten = false;
        for (int row = position[0] - 1; (row <= position[0] + 1) && !hasEaten; row++) {
            for (int column = position[1] - 1; (column <= position[1] + 1) && !hasEaten; column++) {
                if (position[1] == column && position[0] == row) {
                    continue;
                }
                partialRow = (row % rows) < 0 ? (row % rows) + rows : (row % rows);
                partialColumn = (column % columns) < 0 ? (column % columns) + columns : (column % columns);
                if (worldCopy[partialRow][partialColumn].type == WorldTypes.Plant.value) {
                    increaseLife(10);
                    hasEaten = true;
                    worldCopy[partialRow][partialColumn].decreaseLife(10)
                }
            }
        }
    }

    void reproduce() {
        int rows = worldCopy.length
        int columns = worldCopy[0].length
        int partialRow;
        int partialColumn;
        boolean hasBorn = false;
        if (life > 25) {
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
                        decreaseLife(15);
                    }
                }
            }
        }
    }
}
