package mx.ipn.escom.complexsystems.scripts.generator

import com.gmongo.GMongo

def mongo = new GMongo()
def db = mongo.getDB("GameOfLifeHistory");

def size = 4
def allStates = db["states_$size"].find([contains: [$gt: 0]], [decimalState: 1, _id: 0])

def statesMap = [:]
for (state in allStates) {
    def golState = db["states_$size"].findOne([decimalState: state.decimalState]);
    statesMap["${state.decimalState}"] = [state.decimalState]
    while (statesMap["${state.decimalState}"].count(golState.decimalState) <= 1) {
        golState = db["states_$size"].findOne([decimalState: golState.contains]);
        statesMap["${state.decimalState}"].add(golState.decimalState);
    }
}
println("Finished getting all the states")
def folder = "/home/alberto/Desktop/ComplexSystems/GameOfLife/"
def currentIndex = "${size}x${size}/"
def collected = statesMap.findAll { st ->
    return st.value.count(0) == 0
}
println("Finished filtering all the states")
def file
//
new File(folder.concat(currentIndex)).mkdirs();
//
for (state in collected) {
    file = new File(folder.concat(currentIndex) + "GoL_${state.key}.dot")
    file.write("diagraph{ ${state.value.join(" -> ")} }")
}
println("Finishing generating dot files")