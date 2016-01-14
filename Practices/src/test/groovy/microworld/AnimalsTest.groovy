package microworld

import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.elements.Carnivore
import mx.ipn.escom.complexsystems.microworld.definition.elements.Corpse
import mx.ipn.escom.complexsystems.microworld.definition.elements.Ground
import mx.ipn.escom.complexsystems.microworld.definition.elements.Herbivore
import mx.ipn.escom.complexsystems.microworld.definition.elements.Plant
import mx.ipn.escom.complexsystems.microworld.definition.elements.Water
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import java.lang.Void as Should

/**
 * Created by alberto on 10/01/16.
 */
class AnimalsTest extends Specification {
    @Shared
    WorldElement plant
    @Shared
    WorldElement herbivore
    @Shared
    WorldElement carnivore
    @Shared
    WorldElement carnivore2
    @Shared
    WorldElement corpse
    @Shared
    WorldElement[][] world = new WorldElement[5][5]
    @Shared
    Operations operations = Operations.instance

    def setup() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 0 && j == 0) {
                    world[i][j] = new Water(position: [0, 0])
                } else {
                    world[i][j] = new Ground(position: [i, j])
                }
            }
        }
        //
        plant = new Plant(position: [1, 0])
        world[1][0] = plant
        //
        herbivore = new Herbivore()
        herbivore.position = [0, 1]
        world[0][1] = herbivore
        herbivore.worldCopy = world
        //
        carnivore = new Carnivore()
        carnivore.position = [4, 4]
        world[4][4] = carnivore
        carnivore.worldCopy = world
        //
        carnivore2 = new Carnivore()
        carnivore2.position = [3, 3]
        world[3][3] = carnivore2
        carnivore2.worldCopy = world
        //
        corpse = new Corpse()
        corpse.position = [2, 2]
        world[2][2] = corpse
        corpse.worldCopy = world
    }


    Should "Die from life points"() {
        given:
        herbivore.life = 1;
        herbivore.decreaseLife(1)
        herbivore = Operations.verifyElement(herbivore)
        expect:
        assert herbivore.class.getSimpleName() == "Corpse"
        assert herbivore.type == WorldTypes.Corpse.value
        assert herbivore.alive == false
        assert herbivore.life == 20
    }

    Should "Drink water from the nearest source"() {
        given:
        int prevLife = herbivore.life
        herbivore.drink()
        herbivore = Operations.verifyElement(herbivore)
        expect:
        assert herbivore.class.getSimpleName() == "Herbivore"
        assert prevLife < herbivore.life
    }

    Should "generate a new Carnivore/Herbivore/Scavenger (reproduction)"() {
        given:
        herbivore.life = 70
        herbivore.reproduce()
        expect:
        world.flatten().count { element -> element.type == WorldTypes.Herbivore.value } > 1
    }

    Should "Move to a new place considering water/food/predator existence"() {
        given:
        List prevPosition = carnivore.position
        carnivore.move()
        expect:
        carnivore.position != prevPosition
    }

    Should "Move to a new place using nearest neighbor information"() {
        given:
        List prevPosition = carnivore.position
        carnivore.moveWithInformation()
        expect:
        carnivore.position != prevPosition
    }

    Should "Eat from nearest food source (Herbivore)/Carnivore/Scavenger"() {
        given:
        int prevLife = herbivore.life
        herbivore.eat()
        expect:
        herbivore.life > prevLife

    }

    @Ignore
    Should "Eat from nearest food source (Carnivore/Scavenger)"() {
        given:
        operations.printMap(world)
        int prevLife = carnivore.life
        carnivore.eat()
        expect:
        operations.printMap(world)
        carnivore.life > prevLife
    }
}
