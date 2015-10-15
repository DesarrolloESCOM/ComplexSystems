package mx.ipn.escom.complexSystems.gameOfLife.engine;

import java.util.Random;

/**
 * Created by alberto on 21/09/15.
 */
public class Generator {
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
}
