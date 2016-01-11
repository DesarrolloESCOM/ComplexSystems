package mx.ipn.escom.complexsystems.microworld.definition.elements

import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 10/01/16.
 */
class Ground implements WorldElement {
    public Ground() {
        this.life = -1;
        this.type = WorldTypes.Ground.value
    }
}
