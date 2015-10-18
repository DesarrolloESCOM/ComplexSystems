package mx.ipn.escom.complexsystems.scripts


import mx.ipn.escom.complexsystems.engine.definition.GameOfLife
import mx.ipn.escom.complexsystems.engine.history.AutomataNode
import com.gmongo.GMongo


def mongo = new GMongo()
def db = mongo.getDB("GameOfLifeHistory");

// db.GameOfLifeHistory.insert([])
String nineZeros = "";
def size = 5
//db["states_$size"].drop()
(0..size*size-1).each{it -> 
    nineZeros = nineZeros.concat("0");
}

def maxValue = Math.pow(2,size*size) - 1
List<Integer> allStates = (((Integer)(2*maxValue/4)) .. ((Integer)(3*maxValue/4)))
//
GameOfLife gol = new GameOfLife()
AutomataNode node = new AutomataNode();
println "Started GoL_5x5_3 ${new Date()}"
for(state in allStates) {
    String binaryNumber = Integer.toString(state,2);
    //
    gol.init((nineZeros.concat(binaryNumber)).substring(binaryNumber.length()).toList().each{it-> Integer.parseInt(it)}.collate(size) as int[][]);
    //
    node.decimalState = state
    node.binaryState = binaryNumber
    node.isFinal = false
    node.hits = 0
    node.neighborhood = gol.neighborhood
    //
    gol.task();
    //
    node.contains = Integer.parseInt(gol.neighborhood.flatten().join(""),2)
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