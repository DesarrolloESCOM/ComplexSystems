import mx.ipn.escom.complexSystems.simulation.engine.definition.Diffusion
import spock.lang.Specification

/**
 * Created by alberto on 15/10/15.
 */
class DiffusionRuleTest extends Specification {
    def "Should get all the neighbors from a given cell "() {
        given:
        short[][] population = [
                [1, 1, 0],
                [0, 1, 0],
                [0, 0, 1],
        ]
        Diffusion diffusion = Diffusion.getInstance()
        diffusion.neighborhood = population
        diffusion.rows = 3
        diffusion.columns = 3

        expect:
        diffusion.getNumberOfNeighbors(x, y) == result
        where:
        x | y || result
        0 | 0 || 3
        0 | 2 || 4
        2 | 2 || 3
        2 | 1 || 4
    }

    def "Should apply diffusion rules using a defined configuration"() {
        given:
        short[][] givenPopulation = [
                [1, 0, 0, 0, 0],
                [0, 0, 0, 0, 1],
                [1, 0, 1, 0, 0],
                [0, 0, 0, 1, 0],
                [0, 0, 0, 0, 0],
        ]
        Diffusion diffusion = Diffusion.getInstance()
        diffusion.neighborhood = givenPopulation
        diffusion.rows = 5
        diffusion.columns = 5
        expect:
        //
        diffusion.task()
        diffusion.neighborhood == result.currentPopulation
        diffusion.newAlive == result.newAlive as ArrayList<int[]>
        diffusion.newDeath == result.newDeath as ArrayList<int[]>
        diffusion.alive == result.alive
        where:
        result = [
                'currentPopulation': [
                        [0, 0, 0, 0, 0,],
                        [1, 1, 0, 0, 1,],
                        [0, 0, 0, 1, 1,],
                        [0, 0, 0, 0, 0,],
                        [0, 0, 0, 0, 0,]],
                'newAlive'         : [[1, 0], [1, 1], [1, 4], [2, 3], [2, 4]],
                'newDeath'         : [[0, 0], [2, 0], [2, 2], [3, 3]],
                'alive'            : 5]
    }
}
