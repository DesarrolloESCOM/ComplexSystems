package mx.ipn.escom.complexsystems.scripts.diffusion

import mx.ipn.escom.complexsystems.engine.definition.Diffusion
import com.gmongo.GMongo


def mongo = new GMongo()
def db = mongo.getDB("DiffusionRuleHistory");

def size = 5

String paddingZeros = "";

db["states_$size"].drop()
//
(0..size * size - 1).each { it ->
    paddingZeros = paddingZeros.concat("0");
}

def maxValue = Math.pow(2, size * size) - 1
List<Integer> allStates = (((Integer) (maxValue / 4) + 1)..((Integer) (2 * maxValue / 4)))

Diffusion diffusion = new Diffusion()
Map node = [:]
println "Started Diffusion_5x5_2 ${new Date()}"
for (state in allStates) {
    String binaryNumber = Integer.toString(state, 2);
    //
    diffusion.init((nineZeros.concat(binaryNumber)).substring(binaryNumber.length()).toList().each { it -> Integer.parseInt(it) }.collate(size) as int[][]);
    //
    node.decimalState = state
    node.binaryState = binaryNumber
    node.isFinal = false
    node.hits = 0
    node.neighborhood = diffusion.neighborhood
    //
    diffusion.task();
    //
    node.contains = Integer.parseInt(diffusion.neighborhood.flatten().join(""), 2)
    node.calculated = 2;
    def properties = node.properties.findAll { property ->
        if (!(property.key in ["metaClass", "class"])) {
            return true
        } else {
            return false
        }
    }
    db["states_$size"].insert(properties)
}
println "Done"