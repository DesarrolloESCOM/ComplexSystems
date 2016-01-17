package mx.ipn.escom.complexsystems.microworld.definition.elements

import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 9/01/16.
 */
class Carnivore implements WorldElement {

    public Carnivore() {
        this.sightRange = 3;
        this.life = 50;
        this.type = WorldTypes.Carnivore.value;
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
                if (worldCopy[partialRow][partialColumn].type in [WorldTypes.Herbivore.value]) {
                    increaseLife(20);
                    hasEaten = true;
                    // the carnivore kills the herbivore
                    WorldElement element = worldCopy[partialRow][partialColumn]
                    worldCopy[partialRow][partialColumn].die()
                    // changes the type considering that the herbivore now has become a corpse!
                    worldCopy[partialRow][partialColumn] = operations.verifyElement(element)
                    break;
                }
            }
        }
    }
}
