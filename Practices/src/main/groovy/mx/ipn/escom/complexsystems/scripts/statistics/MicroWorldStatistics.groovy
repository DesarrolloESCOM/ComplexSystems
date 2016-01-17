package mx.ipn.escom.complexsystems.scripts.statistics

import mx.ipn.escom.complexsystems.microworld.definition.MicroWorld
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement

/**
 * Created by alberto on 14/01/16.
 */

def numberOfGenerations = 5000

MicroWorld microWorld = MicroWorld.instance
WorldElement[][] world = microWorld.operation.getMapFromImage("/home/alberto/Desktop/Huge.png")
microWorld.world = world
microWorld.operation.generateMicroWorldAnimals(0.0001, microWorld.world)
microWorld.init(world);

def file = new File("/home/alberto/Desktop/MicroWorld-0_0001.csv")
file.write("generation, Herbivore, Carnivore, Scavenger, Corpse, Plant\n")
while (true) {
    Map prevStatistics = microWorld.getStatistics()
    file.append("${microWorld.generation},${prevStatistics.Herbivore}, ${prevStatistics.Carnivore}, ${prevStatistics.Scavenger}, ${prevStatistics.Corpse}, ${prevStatistics.Plant}\n")
    microWorld.task()
    prevStatistics = microWorld.statistics
    if (microWorld.generation > numberOfGenerations) {
        file.append("${microWorld.generation},${prevStatistics.Herbivore}, ${prevStatistics.Carnivore}, ${prevStatistics.Scavenger}, ${prevStatistics.Corpse}, ${prevStatistics.Plant}\n")
        break;
    }
}

microWorld.generation = 0
world = microWorld.operation.getMapFromImage("/home/alberto/Desktop/Huge.png")
microWorld.world = world
microWorld.operation.generateMicroWorldAnimals(0.001, microWorld.world)
microWorld.init(world);
file = new File("/home/alberto/Desktop/MicroWorld-0_001.csv")
file.write("generation, Herbivore, Carnivore, Scavenger, Corpse, Plant\n")
while (true) {
    Map prevStatistics = microWorld.getStatistics()
    file.append("${microWorld.generation},${prevStatistics.Herbivore}, ${prevStatistics.Carnivore}, ${prevStatistics.Scavenger}, ${prevStatistics.Corpse}, ${prevStatistics.Plant}\n")
    microWorld.task()
    prevStatistics = microWorld.statistics
    if (microWorld.generation > numberOfGenerations) {
        file.append("${microWorld.generation},${prevStatistics.Herbivore}, ${prevStatistics.Carnivore}, ${prevStatistics.Scavenger}, ${prevStatistics.Corpse}, ${prevStatistics.Plant}\n")
        break;
    }
}

microWorld.generation = 0
world = microWorld.operation.getMapFromImage("/home/alberto/Desktop/Huge.png")
microWorld.world = world
microWorld.operation.generateMicroWorldAnimals(0.01, microWorld.world)
microWorld.init(world);
file = new File("/home/alberto/Desktop/MicroWorld-0_01.csv")
file.write("generation, Herbivore, Carnivore, Scavenger, Corpse, Plant\n")
while (true) {
    Map prevStatistics = microWorld.getStatistics()
    file.append("${microWorld.generation},${prevStatistics.Herbivore}, ${prevStatistics.Carnivore}, ${prevStatistics.Scavenger}, ${prevStatistics.Corpse}, ${prevStatistics.Plant}\n")
    microWorld.task()
    prevStatistics = microWorld.statistics
    if (microWorld.generation > numberOfGenerations) {
        file.append("${microWorld.generation},${prevStatistics.Herbivore}, ${prevStatistics.Carnivore}, ${prevStatistics.Scavenger}, ${prevStatistics.Corpse}, ${prevStatistics.Plant}\n")
        break;
    }
}

microWorld.generation = 0
world = microWorld.operation.getMapFromImage("/home/alberto/Desktop/Huge.png")
microWorld.world = world
microWorld.operation.generateMicroWorldAnimals(0.1, microWorld.world)
microWorld.init(world);
file = new File("/home/alberto/Desktop/MicroWorld-0_1.csv")
file.write("generation, Herbivore, Carnivore, Scavenger, Corpse, Plant\n")
while (true) {
    Map prevStatistics = microWorld.getStatistics()
    file.append("${microWorld.generation},${prevStatistics.Herbivore}, ${prevStatistics.Carnivore}, ${prevStatistics.Scavenger}, ${prevStatistics.Corpse}, ${prevStatistics.Plant}\n")
    microWorld.task()
    prevStatistics = microWorld.statistics
    if (microWorld.generation > numberOfGenerations) {
        file.append("${microWorld.generation},${prevStatistics.Herbivore}, ${prevStatistics.Carnivore}, ${prevStatistics.Scavenger}, ${prevStatistics.Corpse}, ${prevStatistics.Plant}\n")
        break;
    }
}