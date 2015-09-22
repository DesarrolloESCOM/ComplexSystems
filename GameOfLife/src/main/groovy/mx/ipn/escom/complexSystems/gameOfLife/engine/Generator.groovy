package mx.ipn.escom.complexSystems.gameOfLife.engine

/**
 * Created by alberto on 21/09/15.
 */
class Generator {
    short[][] generateRandomArray(int width, int height) {
        int finalWidth = width + 2
        int finalHeight = height + 2
        short[][] randomArray = new short[finalWidth][finalHeight];
        for (int i = 1; i <= width; i++) {
            for (int j = 1; j <= height; j++) {
                randomArray = ((int) (10 * Math.random())) % 2 == 0 ? 1 : 0;
            }
        }

        return randomArray
    }

    short[][] generateFullArray(short[][] originalArray) {
        short currentWidth = originalArray[0].length
        short currentHeight = originalArray.length
        short finalWidth = currentWidth + 2
        short finalHeight = currentHeight + 2
        short[][] fullArray = new short[finalWidth][finalHeight];
        //Copying the array content into the new array
        for (int i = 0; i < currentWidth; i++) {
            for (int j = 0; j < currentHeight; j++) {
                fullArray[i + 1][j + 1] = originalArray[i][j]
            }
        }
        // Copying the corners
        fullArray[0][0] = fullArray[currentWidth][currentHeight]
        fullArray[finalWidth - 1][finalHeight - 1] = fullArray[1][1]
        fullArray[0][finalHeight - 1] = fullArray[currentWidth][0]
        fullArray[finalWidth - 1][0] = fullArray[0][currentHeight]
        // Copying the rows
        for (int i = 1; i <= currentWidth; i++) {
            fullArray[i][finalWidth - 1] = fullArray[i][1]
            fullArray[i][0] = fullArray[i][currentWidth]
        }
        // Copying the columns
        println "--------------------------------------------------------------------------"
        println(fullArray)
        println "--------------------------------------------------------------------------"
        for (int j = 1; j <= currentHeight; j++) {
            fullArray[finalHeight - 1][j] = fullArray[1][j]
            fullArray[0][j] = fullArray[currentHeight][j]
        }
        return fullArray
    }
}
