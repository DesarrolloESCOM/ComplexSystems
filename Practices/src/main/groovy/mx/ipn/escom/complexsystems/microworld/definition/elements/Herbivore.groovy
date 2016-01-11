package mx.ipn.escom.complexsystems.microworld.definition.elements

import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 9/01/16.
 */
class Herbivore implements WorldElement {
    public Herbivore() {
        this.sightRange = 1;
        this.life = 50;
        this.type = WorldTypes.Herbivore.value;
        this.canonicalName = "${Operations.elementsPackageName}.${this.class.getSimpleName()}"
    }
}
