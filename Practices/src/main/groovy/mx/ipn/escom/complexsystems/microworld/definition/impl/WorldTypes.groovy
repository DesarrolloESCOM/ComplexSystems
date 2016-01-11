package mx.ipn.escom.complexsystems.microworld.definition.impl

/**
 * Created by alberto on 9/01/16.
 */
enum WorldTypes {
    Ground,
    Water,
    Plant,
    Carnivore,
    Herbivore,
    Scavenger,
    Corpse

    private int value;

    int getValue() {
        return this.value;
    }

    private WorldTypes() {
        this.value = ordinal();
    }
}