package mx.ipn.escom.complexsystems.microworld.definition.elements

import mx.ipn.escom.complexsystems.engine.Operations
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 9/01/16.
 */
class Carnivore implements WorldElement {

    public Carnivore() {
        this.sightRange = 2;
        this.life = 50;
        this.type = WorldTypes.Carnivore.value;
        this.canonicalName = "${Operations.elementsPackageName}.${this.class.getSimpleName()}"
    }
}
