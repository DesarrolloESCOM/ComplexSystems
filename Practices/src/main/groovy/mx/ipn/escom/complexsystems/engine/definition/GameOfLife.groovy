package mx.ipn.escom.complexsystems.engine.definition

import mx.ipn.escom.complexsystems.engine.impl.Automata

/**
 * Created by alberto on 21/09/15.
 */

public class GameOfLife implements Automata {
    // Game of life rules
    int S_MIN_VALUE = 2;
    int S_MAX_VALUE = 3;

    private static GameOfLife instance = null;

    void task() {
        this.generation += 1;
        this.newAlive.clear();
        this.newDeath.clear();
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

    public static GameOfLife getInstance() {
        if (instance == null) {
            instance = new GameOfLife();
        }
        return instance;
    }
}
