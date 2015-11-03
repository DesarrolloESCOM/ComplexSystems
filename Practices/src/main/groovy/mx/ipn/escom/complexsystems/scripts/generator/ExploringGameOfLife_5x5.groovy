package mx.ipn.escom.complexsystems.scripts.generator

/**
 * Created by alberto on 19/10/15.
 */
import com.gmongo.GMongo

def mongo = new GMongo()
def db = mongo.getDB("GameOfLifeHistory");

def size = 5
def allStates = db["states_$size"].find([contains: [$gt: 0]], [decimalState: 1, _id: 0])

def folder = "/home/alberto/ComplexSystems/GameOfLife/"
def currentIndex = "${size}x${size}/"
def file
new File(folder.concat(currentIndex)).mkdirs();
println("Done making dirs")
for (state in allStates) {
    def golState = db["states_$size"].findOne([decimalState: state.decimalState]);
    def currentStates = [state.decimalState]
    def write = true
    while (currentStates.count(golState.decimalState) <= 1) {
        golState = db["states_$size"].findOne([decimalState: golState.contains]);
        if (golState.decimalState == 0) {
            write = false;
            break;
        }
        if (currentStates[-1] == golState.decimalState) {
            write = false;
            break;
        }
        currentStates.add(golState.decimalState);
    }
    if (write) {
        file = new File(folder.concat(currentIndex) + "GoL_${state.decimalState}.dot")
        file.write("digraph { ${currentStates.join(" -> ")} }")
    }
}
println("Finishing generating dot files")