package mx.ipn.escom.complexsystems.scripts.statistics

import mx.ipn.escom.complexsystems.engine.definition.Diffusion

/**
 * Created by alberto on 19/10/15.
 */
def size = 500
def epsilon = 5
Diffusion diffusion = new Diffusion()
diffusion.init(0.1 as float, size, size);
def file = new File("/home/alberto/Desktop/DR_01.csv")
file.write("generation, population\n")
while (true) {
    prevAlive = diffusion.alive
    file.append("${diffusion.generation},${diffusion.alive}\n")
    diffusion.task()
    if ((Math.abs(diffusion.alive - prevAlive) < epsilon) || diffusion.generation > 1000) {
        file.append("${diffusion.generation},${diffusion.alive}\n")
        break;
    }
}

diffusion.generation = 0
diffusion.alive = 0
diffusion.init(0.2 as float, size, size);
file = new File("/home/alberto/Desktop/DR_02.csv")
file.write("generation, population\n")
while (true) {
    prevAlive = diffusion.alive
    file.append("${diffusion.generation},${diffusion.alive}\n")
    diffusion.task()
    if ((Math.abs(diffusion.alive - prevAlive) < epsilon) || diffusion.generation > 1000) {
        file.append("${diffusion.generation},${diffusion.alive}\n")
        break;
    }
}

diffusion.generation = 0
diffusion.alive = 0
diffusion.init(0.5 as float, size, size);
file = new File("/home/alberto/Desktop/DR_05.csv")
file.write("generation, population\n")
while (true) {
    prevAlive = diffusion.alive
    file.append("${diffusion.generation},${diffusion.alive}\n")
    diffusion.task()
    if ((Math.abs(diffusion.alive - prevAlive) < epsilon) || diffusion.generation > 1000) {
        file.append("${diffusion.generation},${diffusion.alive}\n")
        break;
    }
}

diffusion.generation = 0
diffusion.alive = 0
diffusion.init(0.75 as float, size, size);
file = new File("/home/alberto/Desktop/DR_075.csv")
file.write("generation, population\n")
while (true) {
    prevAlive = diffusion.alive
    file.append("${diffusion.generation},${diffusion.alive}\n")
    diffusion.task()
    if ((Math.abs(diffusion.alive - prevAlive) < epsilon) || diffusion.generation > 1000) {
        file.append("${diffusion.generation},${diffusion.alive}\n")
        break;
    }
}