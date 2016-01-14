package microworld
/**
 * Created by alberto on 9/01/16.
 */

import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.elements.Ground
import mx.ipn.escom.complexsystems.microworld.definition.elements.Plant
import mx.ipn.escom.complexsystems.microworld.definition.elements.Water
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import java.lang.Void as Should

class NatureElementsTest extends Specification {

    @Shared
    def water = new Water([type: WorldTypes.Water.getValue()])
    def ground = new Ground([type: WorldTypes.Ground.getValue()])
    def plant = new Plant([type: WorldTypes.Plant.getValue()])
    WorldElement[][] world = [
            [water, water, water, plant, ground, ground],
            [water, water, plant, ground, ground, ground],
            [plant, plant, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground, ground],
            [ground, ground, ground, ground, ground, ground],
    ]
    WorldElement[][] testImage = [
            [water, water, ground, ground],
            [water, plant, plant, plant],
            [ground, ground, ground, ground],
            [ground, ground, ground, ground]
    ]
    Operations operations = Operations.instance
    float seed = 0.10;

    Should "generate the world from a given image"() {
        expect:
        def mapFromImage = operations.getMapFromImage("/home/alberto/Desktop/Mini.png");
        assert mapFromImage.toString() == testImage.toString()
    }

    Should "generate 'N' Carnivore, Herbivore, Scavengers "() {
        expect:
        def result = operations.generateMicroWorldAnimals(seed, world);
        assert result.statistics.Carnivore >= 0
        assert result.statistics.Herbivore >= 0
        assert result.statistics.Scavenger >= 0
        assert result.statistics.Corpse >= 0

    }
    // Special events
    @Ignore
    Should "Remove a plant and convert it into ground"() {

    }

}
