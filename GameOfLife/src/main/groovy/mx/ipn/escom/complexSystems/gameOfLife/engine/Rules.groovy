package mx.ipn.escom.complexSystems.gameOfLife.engine
/**
 * Created by alberto on 21/09/15.
 */
class Rules {
    short[][] neighborhood

    Rules(short[][] neighborhood) {
        this.neighborhood = neighborhood
    }

    short getNumberOfNeighbors(int x, int y) {
        // index are not given via zero index!!
        short realPosX = x + 1
        short realPosY = y + 1
        short numberOfNeighbors = 0
        println(this.neighborhood)
        for (short i = x; i < x + 3; i++) {
            for (short j = y; j < y + 3; j++) {
                if (realPosX == i && realPosY == j) {
                    continue;
                }
                if (this.neighborhood[i][j] == 1) {
                    numberOfNeighbors += 1
                }
            }
        }
        return numberOfNeighbors
    }
}
