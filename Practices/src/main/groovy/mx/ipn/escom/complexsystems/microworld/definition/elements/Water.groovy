package mx.ipn.escom.complexsystems.microworld.definition.elements

import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 10/01/16.
 */
class Water implements WorldElement {
    public Water() {
        this.life = -1;
        this.type = WorldTypes.Water.value
    }
}
