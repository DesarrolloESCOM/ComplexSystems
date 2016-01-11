package mx.ipn.escom.complexsystems.microworld.definition.elements

import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 9/01/16.
 */
class Herbivore implements WorldElement {
    public Herbivore() {
        this.sightRange = 1;
        this.life = 50;
        this.food = 50;
        this.water = 50;
        this.type = WorldTypes.Herbivore.value;
    }

    void decreaseLife(int value) {
        this.life -= value;
        if (life <= 0) {
            this.die()
        }
    }

    void die() {
        this.type = WorldTypes.Corpse.value
        this.alive = false
    }

}
