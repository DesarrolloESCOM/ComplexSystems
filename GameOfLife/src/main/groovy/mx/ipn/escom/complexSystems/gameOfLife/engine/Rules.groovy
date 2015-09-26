package mx.ipn.escom.complexSystems.gameOfLife.engine
/**
 * Created by alberto on 21/09/15.
 */
class Rules implements AutomataConstraints {
    short[][] neighborhood

    Rules(short[][] neighborhood) {
        this.neighborhood = neighborhood
    }

    short getNumberOfNeighbors(int x, int y) {
        // index are not given via zero index!!
        short realPosX = x + 1
        short realPosY = y + 1
        short numberOfNeighbors = 0
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

    @Override
    short[][] gameOfLife() {
        println this.neighborhood.length
        def newAlive = []
        def newDeath = []
        //Considering the extra file and row
        for (short i = 1; i < width - 1; i++) {
            for (short j = 1; i < height - 1; j++) {
                short cellNeighbors = this.getNumberOfNeighbors(i, j)
                if (cellNeighbors < 2 || cellNeighbors >= 4) { // Dies of loneliness or Overpopulation
                    newDeath.add([i, j])
                    continue;
                }
                /*
                if (cellNeighbors == 2 || cellNeighbors == 3) { // Dies of loneliness or Overpopulation
                    newDeath.add([i, j])
                    continue;
                }*/
                if (cellNeighbors == 3) { // a new cell borns
                    newAlive.add([i, j])
                    continue;
                }
            }
        }
    }
}
