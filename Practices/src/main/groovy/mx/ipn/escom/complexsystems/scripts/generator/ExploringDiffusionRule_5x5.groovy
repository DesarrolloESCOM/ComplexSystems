package mx.ipn.escom.complexsystems.scripts.generator

/**
 * Created by alberto on 19/10/15.
 */
import com.gmongo.GMongo

def mongo = new GMongo()
def db = mongo.getDB("DiffusionRuleHistory");

def size = 5
def allStates = db["states_$size"].find([contains: [$gt: 0]], [decimalState: 1, _id: 0]).limit(7000000)

def folder = "/media/alberto/ADATA HD710/ComplexSystems/DiffusionRule/"
def currentIndex = "${size}x${size}/"
def file

new File(folder.concat(currentIndex)).mkdirs();
println("Done making dirs")
for (state in allStates) {
    def golState = db["states_$size"].findOne([decimalState: state.decimalState]);
    def currentStates = [state.decimalState]
    while (currentStates.count(golState.decimalState) <= 1) {
        golState = db["states_$size"].findOne([decimalState: golState.contains]);
        currentStates.add(golState.decimalState);
    }
    if (currentStates.count(0) == 0) {
        file = new File(folder.concat(currentIndex) + "DR_${state.decimalState}.dot")
        file.write("digraph { ${currentStates.join(" -> ")} }")
    }


}
println("Finishing generating dot files")