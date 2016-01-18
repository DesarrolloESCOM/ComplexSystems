package mx.ipn.escom.complexsystems.microworld.definition

import mx.ipn.escom.complexsystems.microworld.definition.elements.Corpse
import mx.ipn.escom.complexsystems.microworld.definition.elements.Plant
import mx.ipn.escom.complexsystems.microworld.definition.impl.MicroWorldAutomata
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 9/01/16.
 */
@Singleton
class MicroWorld implements MicroWorldAutomata {

    void task() {
        this.generation += 1;
        currentElements = [
                "Plant"    : [],
                "Corpse"   : [],
                "Scavenger": [],
                "Herbivore": [],
                "Carnivore": [],
                "Water"    : [],
                "Ground"   : []];
        this.statistics = [
                "Plant"    : 0,
                "Corpse"   : 0,
                "Scavenger": 0,
                "Herbivore": 0,
                "Carnivore": 0,
                "Water"    : 0,
                "Ground"   : 0]

        Random random = new Random()
        int actionSize = this.operation.actionsOrder.size()
        rows = world.length
        columns = world[0].length
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (world[i][j].type == WorldTypes.Water.value) {
                    continue;
                }
                if (world[i][j].type == WorldTypes.Ground.value) {
                    currentElements["${WorldTypes.values()[world[i][j].type]}"].add(world[i][j])
                    statistics["${WorldTypes.values()[world[i][j].type]}"]++;
                    continue;
                }

                if (world[i][j].type == WorldTypes.Plant.value) {
                    ((Plant) world[i][j]).decreaseLife(1);
                }

                int actionIndex = random.nextInt(actionSize)
                world[i][j] = operation.verifyElement(world[i][j])
                if (world[i][j].type == WorldTypes.Corpse.value) {
                    ((Corpse) world[i][j]).decreasePlantsLifePoints()
                    ((Corpse) world[i][j]).decreaseLife(5)
                }
                if (world[i][j].type in [WorldTypes.Herbivore.value, WorldTypes.Carnivore.value, WorldTypes.Scavenger.value]) {
                    world[i][j]."${operation.actionsOrder[actionIndex]}"()
                }
                currentElements["${WorldTypes.values()[world[i][j].type]}"].add(world[i][j])
                statistics["${WorldTypes.values()[world[i][j].type]}"]++;
            }
        }
    }
}
