package mx.ipn.escom.complexsystems.microworld.definition.impl

/**
 * Created by alberto on 9/01/16.
 */
enum WorldTypes {
    Ground(0),
    Water(1),
    Plant(2),
    Carnivore(3),
    Herbivore(4),
    Scavenger(5),
    Corpse(6)

    private int value;

    WorldTypes(int value) {
        this.value = value
    }

    int getValue() {
        return this.value;
    }
}