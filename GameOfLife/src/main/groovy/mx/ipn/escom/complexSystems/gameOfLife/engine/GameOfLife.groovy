package mx.ipn.escom.complexSystems.gameOfLife.engine
/**
 * Created by alberto on 21/09/15.
 */
class GameOfLife {
    short[][] neighborhood
    short generation = 0
    short width
    short height
    Generator generator = new Generator()

    GameOfLife(short[][] neighborhood) {
        this.neighborhood = neighborhood ?: generator.generateRandomArray(this.width, this.height)
    }

    short getNumberOfNeighbors(int x, int y) {
        short numberOfNeighbors = 0
        short width = neighborhood[0].length
        short height = neighborhood.length
        for (short row = x - 1; row < x + 2; row++) {
            for (short column = y - 1; column < y + 2; column++) {
                if (y == column && x == row) {
                    continue;
                }
                if (this.neighborhood[row % width][column % height] == 1) {
                    numberOfNeighbors += 1
                }
            }
        }
        return numberOfNeighbors
    }

    Map gameOfLife() {
        this.generation += 1
        def newAlive = []
        def newDeath = []
        short alive = 0;
        short width = neighborhood[0].length
        short height = neighborhood.length
        short[][] clonedArray = new short[height][width]
        for (short row = 0; row < width; row++) {
            for (short column = 0; column < height; column++) {
                short cellNeighbors = this.getNumberOfNeighbors(row, column)
                if (cellNeighbors < 2 || cellNeighbors > 3) {
                    // Dies of loneliness or Overpopulation
                    if (this.neighborhood[row][column] == 1) {
                        newDeath.add([row, column])
                    }
                    clonedArray[row][column] = 0;
                    continue;
                }
                if (cellNeighbors == 3 && this.neighborhood[row][column] == 0) {
                    // It was a dead cell, a new one bears
                    newAlive.add([row, column])
                    alive += 1
                    clonedArray[row][column] = 1
                    continue;
                }
                if ((cellNeighbors == 3 || cellNeighbors == 2) && this.neighborhood[row][column] == 1) {
                    // Survives
                    alive += 1
                    clonedArray[row][column] = 1;
                    continue;
                } else { // it was a dead cell, nothing else happens
                    clonedArray[row][column] = 0;
                    continue;
                }

            }
        }
        this.neighborhood = clonedArray
        return [newAlive: newAlive, newDeath: newDeath, currentPopulation: this.neighborhood, alive: alive, generation: this.generation]
    }
}
