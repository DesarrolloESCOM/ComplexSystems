package microworld
/**
 * Created by alberto on 9/01/16.
 */


import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.engine.definition.MicroWorld
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import java.lang.Void as Should

class NatureElementsTest extends Specification {

    @Shared
    def water = [type: WorldTypes.WATER, life: -1]
    def ground = [type: WorldTypes.GROUND, life: -1]
    def vegetation = [type: WorldTypes.VEGETATION, life: 50]
    def world = [
            [water, water, water, vegetation, ground, ground],
            [water, water, vegetation, ground, ground, ground],
            [vegetation, vegetation, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground, ground],
    ]
    MicroWorld microWorld = new MicroWorld()
    Operations operations = new Operations()
    int rows = 6
    int columns = 6;

    @Ignore
    Should "generate the world from a given image"() {
    }

    Should "generate 'N' Carnivore, Herbivore, Scavengers "() {
        given:
        float seed = 0.10;
        float expectedPopulation = rows * columns * seed
        then:
        def result = operations.generateMicroWorldAnimals(seed, world);
        expect:
        Math.abs(result.aliveCarnivore - expected.aliveCarnivore) <= 2
        Math.abs(result.aliveHerbivore - expected.aliveHerbivore) <= 2
        Math.abs(result.aliveScavenger - expected.aliveScavenger) <= 2
        where:
        expected = [
                aliveCarnivore: expectedPopulation,
                aliveHerbivore: expectedPopulation,
                aliveScavenger: expectedPopulation
        ]


    }

    @Ignore
    Should "generate a new Carnivore/Herbivore/Scavenger (reproduction)"() {

    }
    // Move
    @Ignore
    Should "Move to a new place considering food (Vegetation, Herbivore)"() {

    }

    @Ignore
    Should "Move to a new place considering food (Hunting, Carnivore)"() {

    }

    @Ignore
    Should "Move to a new place considering food (Corpse, Scavenger)"() {

    }

    @Ignore
    Should "Move to a new place considering predator presence"() {

    }

    @Ignore
    Should "Move to a new place considering water existence"() {

    }

    @Ignore
    Should "Move to a new place randomly (Considering nothing near of the animal)"() {

    }

    @Ignore
    Should "Move to a new place considering nearest neighbor information"() {

    }
    // Drink
    @Ignore
    Should "Drink water from the nearest weather source"() {

    }
    // Eat
    @Ignore
    Should "Eat flesh from a corpse (Scavenger)"() {

    }

    @Ignore
    Should "Eat from nearest vegetation (Herbivore)"() {

    }

    @Ignore
    Should "Hunt and Eat from nearest animal (Carnivore)"() {

    }

    @Ignore
    Should "Die from starvation"() {

    }
    // Special events
    @Ignore
    Should "Remove a plant and convert it into ground"() {

    }

    @Ignore
    Should "Generate plants randomly after corpse's time of life"() {

    }

    @Ignore
    Should "Decrease plants life points if a corpse is near of them"() {

    }
}
