package mx.ipn.escom.complexsystems.scripts.statistics

import mx.ipn.escom.complexsystems.engine.definition.GameOfLife

/**
 * Created by alberto on 19/10/15.
 */
def size = 500
def epsilon = 5
GameOfLife gameOfLife = new GameOfLife()
gameOfLife.init(0.1 as float, size, size);
def file = new File("/home/alberto/Desktop/GoL_01.csv")
file.write("generation, population\n")
while (true) {
    prevAlive = gameOfLife.alive
    file.append("${gameOfLife.generation},${gameOfLife.alive}\n")
    gameOfLife.task()
    if ((Math.abs(gameOfLife.alive - prevAlive) < epsilon) || gameOfLife.generation > 1000) {
        file.append("${gameOfLife.generation},${gameOfLife.alive}\n")
        break;
    }
}

gameOfLife.generation = 0
gameOfLife.alive = 0
gameOfLife.init(0.2 as float, size, size);
file = new File("/home/alberto/Desktop/GoL_02.csv")
file.write("generation, population\n")
while (true) {
    prevAlive = gameOfLife.alive
    file.append("${gameOfLife.generation},${gameOfLife.alive}\n")
    gameOfLife.task()
    if ((Math.abs(gameOfLife.alive - prevAlive) < epsilon) || gameOfLife.generation > 1000) {
        file.append("${gameOfLife.generation},${gameOfLife.alive}\n")
        break;
    }
}

gameOfLife.generation = 0
gameOfLife.alive = 0
gameOfLife.init(0.5 as float, size, size);
file = new File("/home/alberto/Desktop/GoL_05.csv")
file.write("generation, population\n")
while (true) {
    prevAlive = gameOfLife.alive
    file.append("${gameOfLife.generation},${gameOfLife.alive}\n")
    gameOfLife.task()
    if ((Math.abs(gameOfLife.alive - prevAlive) < epsilon) || gameOfLife.generation > 1000) {
        file.append("${gameOfLife.generation},${gameOfLife.alive}\n")
        break;
    }
}

gameOfLife.generation = 0
gameOfLife.alive = 0
gameOfLife.init(0.75 as float, size, size);
file = new File("/home/alberto/Desktop/GoL_075.csv")
file.write("generation, population\n")
while (true) {
    prevAlive = gameOfLife.alive
    file.append("${gameOfLife.generation},${gameOfLife.alive}\n")
    gameOfLife.task()
    if ((Math.abs(gameOfLife.alive - prevAlive) < epsilon) || gameOfLife.generation > 1000) {
        file.append("${gameOfLife.generation},${gameOfLife.alive}\n")
        break;
    }
}