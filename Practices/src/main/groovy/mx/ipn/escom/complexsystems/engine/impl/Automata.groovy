package mx.ipn.escom.complexsystems.engine.impl

import mx.ipn.escom.complexsystems.engine.Operations

/**
 * Created by alberto on 14/10/15.
 */
trait Automata {
    int[][] neighborhood;
    int generation = 0;
    int rows = 0;
    int columns = 0;
    int alive = 0;
    ArrayList<int[]> newAlive = new ArrayList<>();
    ArrayList<int[]> newDeath = new ArrayList<>();
    boolean start = false;
    Operations operation = new Operations();

    void init(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.neighborhood = neighborhood != null ? neighborhood : this.operation.generateRandomArray(this.rows, this.columns);
    }
    void init(int[][] neighborhood) {
        this.rows = neighborhood.length;
        this.columns = neighborhood[0].length;
        this.neighborhood = neighborhood;
    }

    void init(float seed, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.neighborhood = this.operation.generateSeededArray(seed, this.rows, this.columns);
        this.alive = this.neighborhood.flatten().count(1);
    }

    int getNumberOfNeighbors(int x, int y) { // Considering Moore neighborhood
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

    void resizeNeighborhood(int rows, int columns) {
        this.neighborhood = operation.resizeArray(this.neighborhood, rows, columns);
        this.rows = rows;
        this.columns = columns;
    }

    void task() {}
}