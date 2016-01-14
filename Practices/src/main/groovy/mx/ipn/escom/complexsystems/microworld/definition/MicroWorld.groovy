package mx.ipn.escom.complexsystems.microworld.definition

import mx.ipn.escom.complexsystems.microworld.definition.elements.Corpse
import mx.ipn.escom.complexsystems.microworld.definition.impl.MicroWorldAutomata
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 9/01/16.
 */
@Singleton
class MicroWorld implements MicroWorldAutomata {
    void task() {
        this.generation += 1;
        Random random = new Random()
        int actionSize = this.operation.actionsOrder.size()
        rows = world.length
        columns = world[0].length
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int actionIndex = random.nextInt(actionSize)
                world[i][j] = operation.verifyElement(world[i][j])
                if (world[i][j].type == WorldTypes.Corpse.value) {
                    ((Corpse) world[i][j]).decreasePlantsLifePoints()
                }
                if (world[i][j].type in [WorldTypes.Herbivore.value, WorldTypes.Carnivore.value, WorldTypes.Scavenger.value]) {
                    world[i][j]."${operation.actionsOrder[actionIndex]}"()
                }
            }
        }
    }

    Map getStatistics() {
        Map statistics = [
                "Plant"    : 0,
                "Corpse"   : 0,
                "Scavenger": 0,
                "Herbivore": 0,
                "Carnivore": 0,
                "Water"    : 0,
                "Ground"   : 0]
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String className = world[i][j].class.getSimpleName()
                statistics["$className"]++
            }
        }
        return statistics
    }
}
