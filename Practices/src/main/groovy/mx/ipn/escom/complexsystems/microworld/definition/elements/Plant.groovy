package mx.ipn.escom.complexsystems.microworld.definition.elements

import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 10/01/16.
 */
class Plant implements WorldElement {
    public Plant() {
        this.life = 100;
        this.type = WorldTypes.Plant.getValue()
    }

    void decreaseLife(int value) {
        this.life -= value;
    }

    void die() {
        this.type = WorldTypes.Ground.value
        this.alive = false;
    }
}
