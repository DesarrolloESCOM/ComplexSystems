import mx.ipn.escom.complexsystems.engine.Operations
import spock.lang.Specification

/**
 * Created by alberto on 15/10/15.
 */
class OperationsTest extends Specification {
    def "Should resize or chop a defined array"() {
        given:
        int[][] givenPopulation = [
                [1, 0, 0, 0, 0],
                [0, 0, 0, 0, 1],
                [1, 0, 1, 0, 0],
                [0, 0, 0, 1, 0],
                [0, 0, 0, 0, 0],
        ]
        int[][] smallPopulation = [
                [1, 0, 1, 0, 0],
                [1, 0, 0, 0, 1],
        ]
        Operations operation = new Operations()
        expect:
        operation.resizeArray(givenPopulation, 3, 3) == choppedPopulation
        operation.resizeArray(smallPopulation, 5, 6) == augmentedPopulation
        where:
        choppedPopulation = [
                [1, 0, 0],
                [0, 0, 0],
                [1, 0, 1]
        ] as short[][]
        augmentedPopulation = [
                [1, 0, 1, 0, 0, 0],
                [1, 0, 0, 0, 1, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0, 0]
        ] as short[][]

    }

    def "Should generate a seeded array"() {
        given:
        int rows = 5;
        int columns = 5;
        Operations operation = new Operations()
        float seed_25 = 0.25
        float seed_50 = 0.5
        float seed_75 = 0.75
        float expectedSeed25 = rows * columns * seed_25
        float expectedSeed50 = rows * columns * seed_50
        float expectedSeed75 = rows * columns * seed_75
        expect:
        int seedCount25 = operation.generateSeededArray(seed_25, rows, columns).flatten().findAll { element -> element > 0 }.size()
        int seedCount50 = operation.generateSeededArray(seed_50, rows, columns).flatten().findAll { element -> element > 0 }.size()
        int seedCount75 = operation.generateSeededArray(seed_75, rows, columns).flatten().findAll { element -> element > 0 }.size()
        Math.abs(seedCount25 - expectedSeed25) <= 1
        Math.abs(seedCount50 - expectedSeed50) <= 1
        Math.abs(seedCount75 - expectedSeed75) <= 1

    }
}
