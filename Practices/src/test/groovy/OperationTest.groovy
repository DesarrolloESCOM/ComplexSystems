import mx.ipn.escom.complexsystems.engine.Operation
import spock.lang.Specification

/**
 * Created by alberto on 15/10/15.
 */
class OperationTest extends Specification {
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
        Operation operation = new Operation()
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
        Operation operation = new Operation()
        float seed_25 = 0.25
        float seed_50 = 0.5
        float seed_75 = 0.75
        expect:
        operation.generateSeededArray(seed_25, rows, columns).flatten().findAll { element -> element > 0 }.size() >= rows * columns * seed_25
        operation.generateSeededArray(seed_50, rows, columns).flatten().findAll { element -> element > 0 }.size() >= rows * columns * seed_50
        operation.generateSeededArray(seed_75, rows, columns).flatten().findAll { element -> element > 0 }.size() >= rows * columns * seed_75
    }
}
