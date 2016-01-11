package microworld

import mx.ipn.escom.complexsystems.engine.Operations
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
    Ground ground = new Ground();
    @Shared
    Water water = new Water();
    @Shared
    WorldElement[][] world = [
            [water, new Plant(), ground, ground, ground],
            [new Plant(), ground, ground, ground, ground],
            [ground, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground],
    ]

    Should "Die from life points"() {
        given:
        WorldElement animal = new Herbivore()
        animal.life = 1;
        animal.decreaseLife(1)
        animal = Operations.verifyElement(animal)
        expect:
        assert animal.class.getSimpleName() == "Corpse"
        assert animal.type == WorldTypes.Corpse.value
        assert animal.alive == false
        assert animal.life == 20
    }
    // Drink
    @Ignore
    Should "Drink water from the nearest weather source"() {

    }

    @Ignore
    Should "generate a new Carnivore/Herbivore/Scavenger (reproduction)"() {

    }
    // Move
    @Ignore
    Should "Move to a new place considering predator presence"() {

    }

    @Ignore
    Should "Move to a new place considering water existence"() {

    }

    @Ignore
    Should "Move to a new place randomly (Considering nothing near of the animal)"() {

    }
}
