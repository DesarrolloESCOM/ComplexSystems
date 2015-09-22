import mx.ipn.escom.complexSystems.gameOfLife.Rules
import spock.lang.Specification

/**
 * Created by alberto on 21/09/15.
 */
class RulesTest extends Specification {
    def "Should get all the neighbors from a given cell "() {
        given:
        /*
        * The corners will be mirrored and duplicated, this will come
        * from other method explaining further information
        * in Readme file on repository folder
        * */
        short[][] neighborhood = [
                [1, 0, 0, 1, 0],
                [0, 1, 1, 0, 1],
                [0, 0, 1, 0, 0],
                [1, 0, 0, 1, 0],
                [0, 1, 1, 0, 1]
        ]
        Rules rules = new Rules(neighborhood)
        expect:
        rules.getNumberOfNeighbors(x, y) == result
        where:
        x | y || result
        0 | 0 || 3
        0 | 2 || 4
        2 | 2 || 3
        2 | 1 || 4
    }
}
