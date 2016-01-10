package microworld
/**
 * Created by alberto on 9/01/16.
 */


import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.elements.scenario.NatureElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import java.lang.Void as Should

class NatureElementsTest extends Specification {

    @Shared
    def water = new NatureElement([type: WorldTypes.Water.getValue(), life: -1])
    def ground = new NatureElement([type: WorldTypes.Ground.getValue(), life: -1])
    def vegetation = new NatureElement([type: WorldTypes.Plant.getValue(), life: 50])
    WorldElement[][] world = [
            [water, water, water, vegetation, ground, ground],
            [water, water, vegetation, ground, ground, ground],
            [vegetation, vegetation, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground, ground],
    ]
    Operations operations = new Operations()
    int rows = 6
    int columns = 6;
    int currentNatureElements = 9
    float seed = 0.10;
    float expectedPopulation = (rows * columns - currentNatureElements) * seed

    Should "generate the world from a given image"() {
    }

    Should "generate 'N' Carnivore, Herbivore, Scavengers "() {
        expect:
        def result = operations.generateMicroWorldAnimals(seed, world);

        assert result.statistics.Carnivore > 0
        assert result.statistics.Herbivore > 0
        assert result.statistics.Scavenger > 0
        assert result.statistics.Corpse > 0

    }
    // Special events
    @Ignore
    Should "Remove a plant and convert it into ground"() {

    }

}
