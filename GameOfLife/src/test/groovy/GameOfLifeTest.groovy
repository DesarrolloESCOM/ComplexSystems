import mx.ipn.escom.complexSystems.gameOfLife.engine.GameOfLife
import mx.ipn.escom.complexSystems.gameOfLife.engine.Generator
import spock.lang.Specification

/**
 * Created by alberto on 21/09/15.
 */
class GameOfLifeTest extends Specification {
    def "Should get all the neighbors from a given cell "() {
        given:
        /*
        * The corners will be mirrored and duplicated, this will come
        * from other method explaining further information
        * in Readme file on repository folder
        * */
        short[][] population = [
                [1, 1, 0],
                [0, 1, 0],
                [0, 0, 1],
        ]
        GameOfLife gameOfLife = new GameOfLife(population)
        expect:
        gameOfLife.getNumberOfNeighbors(x, y) == result
        where:
        x | y || result
        0 | 0 || 3
        0 | 2 || 4
        2 | 2 || 3
        2 | 1 || 4
    }

    def "Should apply game of life rules using a defined configuration"() {
        given:
        short[][] givenPopulation = [
                [1, 0, 0, 0, 0],
                [0, 0, 0, 0, 1],
                [1, 0, 1, 0, 0],
                [0, 0, 0, 1, 0],
                [0, 0, 0, 0, 0],
        ]
        GameOfLife gameOfLife = new GameOfLife(givenPopulation)

        expect:
        def gameOfLifeResult = gameOfLife.gameOfLife()
        gameOfLifeResult.currentPopulation == result.currentPopulation
        gameOfLifeResult.newAlive == result.newAlive
        gameOfLifeResult.newDeath == result.newDeath
        gameOfLifeResult.alive == result.alive
        where:
        result = [
                'currentPopulation': [
                        [0, 0, 0, 0, 0,],
                        [1, 1, 0, 0, 1,],
                        [0, 0, 0, 1, 1,],
                        [0, 0, 0, 0, 0,],
                        [0, 0, 0, 0, 0,]],
                'newAlive'         : [[1, 0], [1, 1], [2, 3], [2, 4]],
                'newDeath'         : [[0, 0], [2, 0], [2, 2], [3, 3]],
                'alive'            : 5]
    }

    def "Should resize or chop a defined array"() {
        given:
        short[][] givenPopulation = [
                [1, 0, 0, 0, 0],
                [0, 0, 0, 0, 1],
                [1, 0, 1, 0, 0],
                [0, 0, 0, 1, 0],
                [0, 0, 0, 0, 0],
        ]
        short[][] smallPopulation = [
                [1, 0, 0, 0, 0],
                [0, 0, 0, 0, 1],
        ]
        Generator generator = new Generator()
        expect:
        generator.resizeArray(givenPopulation, 3, 3) == choppedPopulation
        //generator.resizeArray(smallPopulation, 5, 6) == augmentedPopulation
        where:
        choppedPopulation = [
                [1, 0, 0],
                [0, 0, 0],
                [1, 0, 1]
        ] as short[][]
        augmentedPopulation = [
                [1, 0, 0, 0, 0],
                [0, 0, 0, 0, 1],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
        ] as short[][]

    }
}
