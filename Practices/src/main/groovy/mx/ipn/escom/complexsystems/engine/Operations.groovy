package mx.ipn.escom.complexsystems.engine

import mx.ipn.escom.complexsystems.microworld.definition.elements.Ground
import mx.ipn.escom.complexsystems.microworld.definition.elements.Plant
import mx.ipn.escom.complexsystems.microworld.definition.elements.Water
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldElement
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

/**
 * Created by alberto on 21/09/15.
 */
@Singleton
public class Operations {
    static String elementsPackageName = "mx.ipn.escom.complexsystems.microworld.definition.elements"
    Map animalsOrder = [0: "Carnivore", 1: "Herbivore", 2: "Scavenger", 3: "Corpse"]
    Map actionsOrder = [0: "drink", 1: "eat", 2: "moveWithInformation", 3: "move", 4: "reproduce"]
    //Map actionsOrder = [0: "drink", 1: "eat", 2: "move", 3: "reproduce"]
    Map movementsReasonMap = [0: "Water", 1: "Plant", 2: "Carnivore", 3: "Herbivore", 4: "Scavenger", 5: "Corpse"]

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
                if (world[row][column].type in [WorldTypes.Water.value, WorldTypes.Plant.value]) {
                    continue;
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
                    WorldElement animalInstance = Class.forName("${elementsPackageName}.${animalType}").newInstance();
                    animalInstance.type = WorldTypes."${animalType}".getValue()
                    animalInstance.position = [row, column]
                    animalInstance.worldCopy = world;
                    world[row][column] = animalInstance;
                }
            }
        }
        return worldComponents
    }

    WorldElement[][] getMapFromImage(String path) {
        File image = new File(path);
        BufferedImage bufferedImage = ImageIO.read(image);
        int height = bufferedImage.getHeight()
        int width = bufferedImage.getWidth()
        WorldElement[][] finalMap = new WorldElement[width][height]
        for (int row = 0; row < width; row++) {
            for (int column = 0; column < height; column++) {
                // The pixels are read in reverse order (?)
                int rgbColor = bufferedImage.getRGB(row, column);
                int red = (rgbColor & 0x00ff0000) >> 16;
                int green = (rgbColor & 0x0000ff00) >> 8;
                int blue = rgbColor & 0x000000ff;
                String hexColor = String.format("%02x%02x%02x", red, green, blue).toUpperCase();
                WorldElement element;
                switch (hexColor) {
                    case "00FF00":
                        element = new Plant()
                        element.position = [row, column]
                        element.type = WorldTypes.Plant.getValue()
                        element.worldCopy = finalMap
                        finalMap[row][column] = element
                        break;
                    case "0000FF":
                        element = new Water()
                        element.position = [row, column]
                        element.type = WorldTypes.Water.getValue()
                        element.worldCopy = finalMap
                        finalMap[row][column] = element
                        break;
                    case "FFDEAD":
                        element = new Ground()
                        element.position = [row, column]
                        element.type = WorldTypes.Ground.getValue()
                        element.worldCopy = finalMap
                        finalMap[row][column] = element
                        break;
                    default:
                        element = new Ground()
                        element.position = [row, column]
                        element.type = WorldTypes.Ground.getValue()
                        element.worldCopy = finalMap
                        finalMap[row][column] = element
                        break;
                }
            }
        }
        return finalMap
    }

    static WorldElement verifyElement(WorldElement element) {
        String elementClassName = element.class.getSimpleName()
        if (element.type != WorldTypes."$elementClassName".value) {
            String newElementClassName = WorldTypes.values()[element.type]
            Map previousProperties = element.properties
            element = Class.forName("${elementsPackageName}.${newElementClassName}").newInstance();
            element.position = previousProperties.position
            element.worldCopy = previousProperties.worldCopy
        }
        return element
    }

    int distance(List source, List destination) {
        try {
            return Math.abs(source[0] - destination[0]) + Math.abs(source[1] - destination[1]);
        } catch (Exception e) {
            return 1
        }

    }

    void printMap(WorldElement[][] world) {
        println "*************************************************"
        for (row in world) {
            for (element in row) {
                print("[${element.type}]")
            }
            println()
        }
        println "*************************************************"
    }
}
