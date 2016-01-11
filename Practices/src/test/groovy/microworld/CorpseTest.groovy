package microworld

import mx.ipn.escom.complexsystems.microworld.definition.elements.Corpse
import mx.ipn.escom.complexsystems.microworld.definition.elements.Ground
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
class CorpseTest extends Specification {
    @Shared
    Ground ground = new Ground();
    @Shared
    Corpse corpse = new Corpse()
    @Shared
    WorldElement[][] world = [
            [ground, new Plant(), ground],
            [new Plant(), corpse, new Plant()],
            [ground, new Plant(), ground],
    ]

    Should "Decrease plants life points if a corpse is near of them"() {
        given:
        corpse.worldCopy = world
        corpse.position = [1, 1]
        int currentPlantLife = world[0][1].life
        corpse.decreasePlantsLifePoints()
        expect:
        assert currentPlantLife > world[0][1].life
    }

    Should "Generate plants randomly after corpse's time of life"() {
        given:
        corpse.life = 1;
        corpse.position = [1, 1]
        corpse.decreaseLife(1);
        expect:
        world.flatten().count { element -> element.type == WorldTypes.Plant.getValue() } >= 4
    }
}
