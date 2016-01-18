package mx.ipn.escom.complexsystems.microworld.definition.elements

import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 10/01/16.
 */
class Plant implements WorldElement {
    public Plant() {
        this.life = 50;
        this.type = WorldTypes.Plant.getValue()
    }

    void decreaseLife(int value) {
        this.life -= value;
        if(this.life < 0) {
            this.die()
        }
    }

    void die() {
        this.type = WorldTypes.Ground.value
        this.alive = false;
    }
}
