package mx.ipn.escom.complexSystems.gameOfLife.engine
/**
 * Created by alberto on 21/09/15.
 */
class GameOfLife {
    short[][] neighborhood

    GameOfLife(short[][] neighborhood) {
        this.neighborhood = neighborhood
    }

    short getNumberOfNeighbors(int x, int y) {
        short numberOfNeighbors = 0
        short width = neighborhood.length
        short height = neighborhood[0].length
        for (short i = x - 1; i < x + 2; i++) {
            for (short j = y - 1; j < y + 2; j++) {
                if (x == i && y == j) {
                    continue;
                }
                if (this.neighborhood[i % width][j % height] == 1) {
                    numberOfNeighbors += 1
                }
            }
        }
        return numberOfNeighbors
    }

    Map gameOfLife() {
        def newAlive = []
        def newDeath = []
        short alive = 0;
        short width = neighborhood.length
        short height = neighborhood[0].length
        short[][] clonedArray = new short[width][height]
        for (short i = 0; i < width; i++) {
            for (short j = 0; j < height; j++) {
                short cellNeighbors = this.getNumberOfNeighbors(i, j)
                if (cellNeighbors < 2 || cellNeighbors > 3) {
                    // Dies of loneliness or Overpopulation
                    if (this.neighborhood[i][j] == 1) {
                        newDeath.add([i, j])
                    }
                    clonedArray[i][j] = 0;
                    continue;
                }
                if (cellNeighbors == 3 && this.neighborhood[i][j] == 0) { // It was a dead cell, a new one bears
                    newAlive.add([i, j])
                    alive += 1
                    clonedArray[i][j] = 1
                    continue;
                }
                if ((cellNeighbors == 3 || cellNeighbors == 2) && this.neighborhood[i][j] == 1) {
                    // Survives
                    alive += 1
                    clonedArray[i][j] = 1;
                    continue;
                } else { // it was a dead cell, nothing else happens
                    clonedArray[i][j] = 0;
                    continue;
                }

            }
        }
        this.neighborhood = clonedArray
        return [newAlive: newAlive, newDeath: newDeath, currentPopulation: this.neighborhood, alive: alive]
    }
}
