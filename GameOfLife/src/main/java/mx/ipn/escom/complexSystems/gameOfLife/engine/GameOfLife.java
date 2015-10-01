package mx.ipn.escom.complexSystems.gameOfLife.engine;

import java.util.ArrayList;

/**
 * Created by alberto on 21/09/15.
 */

public class GameOfLife {
    public int[][] neighborhood;
    public int generation = 0;
    public int rows;
    public int columns;
    public int alive = 0;
    public int S_MIN_VALUE = 2;
    public int S_MAX_VALUE = 3;
    public ArrayList<int[]> newAlive = new ArrayList<>();
    public ArrayList<int[]> newDeath = new ArrayList<>();
    public boolean start = false;
    public Generator generator = new Generator();

    private static GameOfLife instance = null;

    public void init(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.neighborhood = neighborhood != null ? neighborhood : generator.generateRandomArray(this.rows, this.columns);
    }

    int getNumberOfNeighbors(int x, int y) {
        int numberOfNeighbors = 0;
        int partialRow;
        int partialColumn;
        for (int row = x - 1; row < x + 2; row++) {
            for (int column = y - 1; column < y + 2; column++) {
                if (y == column && x == row) {
                    continue;
                }
                partialRow = (row % this.rows) < 0 ? (row % this.rows) + this.rows : (row % this.rows);
                partialColumn = (column % this.columns) < 0 ? (column % this.columns) + this.columns : (column % this.columns);
                if (this.neighborhood[partialRow][partialColumn] == 1) {
                    numberOfNeighbors += 1;
                }
            }
        }
        return numberOfNeighbors;
    }

    public void gameOfLife() {
        this.generation += 1;
        newAlive.clear();
        newDeath.clear();
        int alive = 0;
        int[][] clonedArray = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int cellNeighbors = this.getNumberOfNeighbors(row, column);
                if (cellNeighbors < S_MIN_VALUE || cellNeighbors > S_MAX_VALUE) {
                    // Dies of loneliness or Overpopulation
                    if (this.neighborhood[row][column] == 1) {
                        int[] index = new int[2];
                        index[0] = row;
                        index[1] = column;
                        newDeath.add(index);
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
                    int[] index = new int[2];
                    index[0] = row;
                    index[1] = column;
                    newAlive.add(index);
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
        this.alive = alive;
    }


    public void resizeNeighborhood(int rows, int columns) {
        this.neighborhood = generator.resizeArray(this.neighborhood, rows, columns);
        this.rows = rows;
        this.columns = columns;
    }

    public static GameOfLife getInstance() {
        if (instance == null) {
            instance = new GameOfLife();
        }
        return instance;
    }
}
