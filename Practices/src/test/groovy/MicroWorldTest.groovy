/**
 * Created by alberto on 9/01/16.
 */
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import java.lang.Void as Should

@Ignore
class MicroWorldTest extends Specification {

    @Shared
    def world = [
            [1, 1, 1, 2, 0, 0],
            [1, 1, 2, 0, 0, 0],
            [2, 2, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0]
    ]


    Should "generate the world from a given image"() {
    }

    Should "generate 'N' Carnivore, Herbivore, Scavengers "() {
    }

    Should "generate a new Carnivore/Herbivore/Scavenger (reproduction)"() {

    }
    // Move
    Should "Move to a new place considering food (Vegetation, Herbivore)"() {

    }

    Should "Move to a new place considering food (Hunting, Carnivore)"() {

    }

    Should "Move to a new place considering food (Corpse, Scavenger)"() {

    }

    Should "Move to a new place considering predator presence"() {

    }

    Should "Move to a new place considering water existence"() {

    }

    Should "Move to a new place randomly (Considering nothing near of the animal)"() {

    }

    Should "Move to a new place considering nearest neighbor information"() {

    }
    // Drink
    Should "Drink water from the nearest weather source"() {

    }
    // Eat
    Should "Eat flesh from a corpse (Scavenger)"() {

    }

    Should "Eat from nearest vegetation (Herbivore)"() {

    }

    Should "Hunt and Eat from nearest animal (Carnivore)"() {

    }

    Should "Die from starvation"() {

    }
    // Special events
    Should "Remove a plant and convert it into ground"() {

    }

    Should "Generate plants randomly after corpse's time of life"() {

    }

    Should "Decrease plants life points if a corpse is near of them"() {

    }
}
