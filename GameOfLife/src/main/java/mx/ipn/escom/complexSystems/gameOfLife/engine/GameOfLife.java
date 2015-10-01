package mx.ipn.escom.complexSystems.gameOfLife.engine

import groovyx.gpars.GParsPool

/**
 * Created by alberto on 21/09/15.
 */
@Singleton
public class GameOfLife {
    public short[][] neighborhood;
    public short generation = 0;
    public short rows;
    public short columns;
    public short alive = 0;
    public short S_MIN_VALUE = 2;
    public short S_MAX_VALUE = 3;
    public ArrayList<int[]> newAlive = new ArrayList<int[]>();
    public ArrayList<int[]> newDeath = new ArrayList<int[]>();
    public boolean start = false;
    public Generator generator = new Generator();

    public void init(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.neighborhood = neighborhood ?: generator.generateRandomArray(this.rows, this.columns)
    }

    short getNumberOfNeighbors(int x, int y) {
        short numberOfNeighbors = 0
        for (short row = x - 1; row < x + 2; row++) {
            for (short column = y - 1; column < y + 2; column++) {
                if (y == column && x == row) {
                    continue;
                }
                if (this.neighborhood[row % this.rows][column % this.columns] == 1) {
                    numberOfNeighbors += 1;
                }
            }
        }
        return numberOfNeighbors;
    }

    public void gameOfLife() {
        this.generation += 1
        newAlive.clear();
        newDeath.clear();
        short alive = 0;
        short[][] clonedArray = new short[rows][columns];
        for (short row = 0; row < rows; row++) {
            for (short column = 0; column < columns; column++) {
                short cellNeighbors = this.getNumberOfNeighbors(row, column)
                if (cellNeighbors < S_MIN_VALUE || cellNeighbors > S_MAX_VALUE) {
                    // Dies of loneliness or Overpopulation
                    if (this.neighborhood[row][column] == 1) {
                        int[] index = new int[2];
                        index[0] = row;
                        index[1] = column;
                        newDeath.add([row, column]);
                    }
                    clonedArray[row][column] = 0;
                    continue;
                }
                if (cellNeighbors == S_MAX_VALUE && this.neighborhood[row][column] == 0) {
                    // It was a dead cell, a new one bears
                    int[] index = new int[2];
                    index[0] = row;
                    index[1] = column;
                    newAlive.add(index);
                    alive += 1;
                    clonedArray[row][column] = 1;
                    continue;
                }
                if ((cellNeighbors == S_MAX_VALUE || cellNeighbors == S_MIN_VALUE) && this.neighborhood[row][column] == 1) {
                    // Survives
                    alive += 1;
                    clonedArray[row][column] = 1;
                    continue;
                } else { // it was a dead cell, nothing else happens
                    clonedArray[row][column] = 0;
                    continue;
                }

            }
        }
        this.neighborhood = clonedArray;
        this.newAlive = newAlive;
        this.newDeath = newDeath;
        this.alive = alive;
    }

    boolean concurrentGameOfLife() {
        GParsPool.withPool(2) { ->
            // TODO implement concurrent gameOfLife method
        };
        return true;
    }

    public void resizeNeighborhood(int rows, int columns) {
        this.neighborhood = generator.resizeArray(this.neighborhood, rows, columns);
        this.rows = rows;
        this.columns = columns;
    }
}
