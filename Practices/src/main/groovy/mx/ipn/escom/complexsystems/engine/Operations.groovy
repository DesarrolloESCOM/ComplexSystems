package mx.ipn.escom.complexsystems.engine

import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

/**
 * Created by alberto on 21/09/15.
 */
public class Operations {
    String animalsPackageName = "mx.ipn.escom.complexsystems.microworld.definition.elements.animals"
    Map typeOrder = [0: "Carnivore", 1: "Herbivore", 2: "Scavenger", 3: "Corpse", 4: "Plant", 5: "Ground", 6: "Water"]
    Map animalsOrder = [0: "Carnivore", 1: "Herbivore", 2: "Scavenger", 3: "Corpse"]
    Map actionsOrder = [0: "drink", 1: "eat", 2: "locationInformation", 3: "move", 4: "reproduce", 5: "generatePlants", 6: "decreaseLife"]

    public int[][] generateRandomArray(int rows, int columns) {
        Random random = new Random();
        int[][] randomArray = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                randomArray[row][column] = (random.nextBoolean() ? 1 : 0);
            }
        }

        return randomArray;
    }

    public int[][] generateSeededArray(float seed, int rows, int columns) {
        Random random = new Random();
        int[][] randomArray = new int[rows][columns];
        int seedPercentage = (rows * columns * seed)
        int hits = 0
        int value
        // Initializing the array
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (hits <= seedPercentage) {
                    value = random.nextInt(rows * columns) <= seedPercentage ? 1 : 0;
                    if (value) {
                        hits++
                    }
                    randomArray[row][column] = value
                } else {
                    randomArray[row][column] = 0
                }


            }
        }
        return randomArray;
    }

    public int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    int[][] resizeArray(int[][] currentArray, int rows, int columns) {
        int currentWidth = currentArray.length;
        int currentHeight = currentArray[0].length;
        int[][] newArray = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                // Avoiding out of bounds exceptions
                if (row >= currentWidth || column >= currentHeight) {
                    newArray[row][column] = 0;
                } else {
                    newArray[row][column] = currentArray[row][column];
                }
            }
        }
        return newArray;
    }

    int getNeighborhoodId(int[][] neighborhood) {
        String binary = (neighborhood as List).flatten().join("");
        return Integer.parseInt(binary, 2)
    }

    int[][] decimalToBinaryArray(int value) {
        String binaryValue = Integer.toString(value, 2);

    }

    Map generateMicroWorldAnimals(float seed, WorldElement[][] world) {
        Random random = new Random();

        int rows = world.length;
        int columns = world[0].length;
        int natureElements = world.flatten().count { element ->
            element.type in [WorldTypes.Water, WorldTypes.Plant]
        };
        int seedPercentage = (rows * columns - natureElements) * seed;
        boolean finished = false;

        int typeSize = this.animalsOrder.size()
        int moves = 0;

        Map worldComponents = [
                statistics: ["Water": 0, "Plant": 0, "Ground": 0, "Carnivore": 0, "Herbivore": 0, "Corpse": 0, "Scavenger": 0],
                elements  : ["Carnivore": [], "Herbivore": [], "Corpse": [], "Scavenger": [], "Plant": [], "Ground": []]
        ]
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (world[row][column].type == WorldTypes.Water.getValue()) {
                    worldComponents.statistics["Water"]++
                    continue;
                } else if (world[row][column].type == WorldTypes.Plant.getValue()) {
                    worldComponents.statistics["Plant"]++
                    worldComponents.elements["Plant"].add(world[row][column])
                    continue;
                } else if (world[row][column].type == WorldTypes.Ground.getValue()) {
                    worldComponents.statistics["Ground"]++
                    worldComponents.elements["Ground"].add(world[row][column])
                }
                // Letting some ground alive!
                if (random.nextInt(5) <= 2) {
                    continue;
                }

                if (!finished) {
                    int elementIndex = random.nextInt(typeSize);
                    String animalType = this.animalsOrder[elementIndex % typeSize]
                    // Generate new animals randomly, considering a uniform distribution
                    while (worldComponents.statistics[animalType] >= seedPercentage) {
                        elementIndex++;
                        animalType = this.animalsOrder[elementIndex % typeSize]
                        moves++;
                        if (moves >= typeSize) {
                            finished = true;
                            break;
                        }
                    }
                    // generate a new animal
                    WorldElement animalInstance = Class.forName("${animalsPackageName}.${animalType}").newInstance();
                    animalInstance.type = WorldTypes."${animalType}".getValue()
                    // Add it into the statistics map
                    worldComponents.statistics[animalType]++
                    worldComponents.statistics["Ground"]--
                    worldComponents.elements["Ground"].remove(world[row][column])
                    world[row][column] = animalInstance;
                    worldComponents.elements[animalType].add(animalInstance)
                }
            }
        }
        for (row in world) {
            println row
        }
        println worldComponents
        return worldComponents
    }
}
