package mx.ipn.escom.complexSystems.gameOfLife.engine

/**
 * Created by alberto on 21/09/15.
 */
class Generator {
    short[][] generateRandomArray(int width, int height) {
        Random random = new Random()
        short[][] randomArray = new short[width][height];
        for (int row = 0; row < width; row++) {
            for (int column = 0; column < height; column++) {
                randomArray[row][column] = random.nextBoolean() ? 1 : 0;
            }
        }

        return randomArray
    }

    public short[][] cloneArray(short[][] src) {
        int length = src.length;
        short[][] target = new short[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    short[][] resizeArray(short[][] currentArray, int rows, int columns) {
        short currentWidth = currentArray.length
        short currentHeight = currentArray[0].length
        short[][] newArray = new short[rows][columns]
        for (short row = 0; row < rows; row++) {
            for (short column = 0; column < columns; column++) {
                // Avoiding out of bounds exceptions
                if (row >= currentWidth || column >= currentHeight) {
                    newArray[row][column] = 0
                } else {
                    newArray[row][column] = currentArray[row][column]
                }
            }
        }
        return newArray
    }
}
