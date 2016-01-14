package mx.ipn.escom.complexsystems.microworld.definition.elements

import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 9/01/16.
 */
class Corpse implements WorldElement {
    public Corpse() {
        this.sightRange = 1;
        this.life = 20;
        this.alive = false;
        this.type = WorldTypes.Corpse.value
        this.canonicalName = "${Operations.elementsPackageName}.${this.class.getSimpleName()}"
    }

    void die() {
        // changing types
        this.alive = false
        this.type = WorldTypes.Ground.value
        //
        Random random = new Random()
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
                if (random.nextBoolean()) {
                    if (worldCopy[partialRow][partialColumn].type == WorldTypes.Ground.getValue()) {
                        WorldElement plant = new Plant()
                        plant.position = [partialRow, partialColumn]
                        plant.worldCopy = worldCopy
                        worldCopy[partialRow][partialColumn] = plant

                    }
                }
            }
        }
    }

    void decreasePlantsLifePoints() {
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
                if (worldCopy[partialRow][partialColumn].type == WorldTypes.Plant.getValue()) {
                    worldCopy[partialRow][partialColumn].decreaseLife(5);
                    worldCopy[partialRow][partialColumn] = Operations.verifyElement(worldCopy[partialRow][partialColumn])
                }
            }
        }
    }
}
