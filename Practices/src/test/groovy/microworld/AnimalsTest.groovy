package microworld

import mx.ipn.escom.complexsystems.engine.Operations
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
            ground = new Ground();
    @Shared
            water = new Water();
    @Shared
    WorldElement herbivore
    @Shared
    WorldElement[][] world = [
            [water, ground, ground, ground, ground],
            [new Plant(), ground, ground, ground, ground],
            [ground, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground],
    ]

    def setup() {
        herbivore = new Herbivore()
        herbivore.position = [0, 1]
        world[0][1] = herbivore
        herbivore.worldCopy = world
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

    @Ignore
    Should "Move to a new place considering water/food/predator existence"() {

    }

    @Ignore
    Should "Eat from nearest food source"() {

    }
}
