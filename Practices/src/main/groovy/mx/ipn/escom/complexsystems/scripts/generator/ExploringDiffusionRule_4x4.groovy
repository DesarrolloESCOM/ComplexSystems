package mx.ipn.escom.complexsystems.scripts.generator

import com.gmongo.GMongo
import groovyx.gpars.GParsPool

//

def mongo = new GMongo()
def db = mongo.getDB("GameOfLifeHistory");

def size = 5

def folder = "/home/alberto/Desktop/ComplexSystems/GameOfLife/"
def currentIndex = "${size}x${size}/"

new File(folder.concat(currentIndex)).mkdirs();
println("Done making dirs")

//
def generateDotFiles(allStates) {
    def mongo = new GMongo()
    def db = mongo.getDB("GameOfLifeHistory");

    def size = 5


    def folder = "/home/alberto/Desktop/ComplexSystems/GameOfLife/"
    def currentIndex = "${size}x${size}/"
    def file

    for (state in allStates) {
        def golState = db["states_$size"].findOne([decimalState: state.decimalState]);
        def currentStates = [state.decimalState]
        while (currentStates.count(golState.decimalState) <= 1) {
            golState = db["states_$size"].findOne([decimalState: golState.contains]);
            currentStates.add(golState.decimalState);
        }
        if (currentStates.count(0) == 0) {
            file = new File(folder.concat(currentIndex) + "GoL_${state.decimalState}.dot")
            file.write("digraph { ${currentStates.join(" -> ")} }")
        }


    }
}
GParsPool.withPool {
    def total = db["states_$size"].find([contains: [$gt: 0]], [decimalState: 1, _id: 0]).count()
    def allStates = db["states_$size"].find([contains: [$gt: 0]], [decimalState: 1, _id: 0])
    generateDotFiles(allStates)
}

println("Finishing generating dot files")