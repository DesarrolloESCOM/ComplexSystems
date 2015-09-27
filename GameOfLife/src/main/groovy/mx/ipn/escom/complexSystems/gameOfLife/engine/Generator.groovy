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

    short[][] resizeArray(short[][] currentArray, int newWidth, int newHeight) {
        short currentWidth = currentArray[0].length
        short currentHeight = currentArray.length
        short[][] newArray = new short[newWidth][newHeight]
        println "************************************************************************************"
        println "New width: $newWidth"
        println "New height: $newHeight"
        println "Prev width: $currentWidth"
        println "Prev height: $currentHeight"
        println "************************************************************************************"
        for (short row = 0; row < newWidth; row++) {
            for (short column = 0; column < newHeight; column++) {
                // Avoiding out of bounds exceptions
                println ">> [$row][$column]"
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
