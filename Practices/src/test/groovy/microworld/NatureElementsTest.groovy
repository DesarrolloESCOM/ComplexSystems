package microworld
/**
 * Created by alberto on 9/01/16.
 */


import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.engine.definition.MicroWorld
import mx.ipn.escom.complexsystems.microworld.definition.elements.scenario.NatureElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import java.lang.Void as Should

class NatureElementsTest extends Specification {

    @Shared
    def water = new NatureElement([type: WorldTypes.WATER, life: -1])
    def ground = new NatureElement([type: WorldTypes.GROUND, life: -1])
    def vegetation = new NatureElement([type: WorldTypes.VEGETATION, life: 50])
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
    // Special events
    @Ignore
    Should "Remove a plant and convert it into ground"() {

    }

}
