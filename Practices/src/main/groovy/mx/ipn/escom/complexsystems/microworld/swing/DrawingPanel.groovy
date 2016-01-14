package mx.ipn.escom.complexsystems.microworld.swing

import mx.ipn.escom.complexsystems.engine.impl.Automata
import mx.ipn.escom.complexsystems.microworld.definition.impl.MicroWorldAutomata
import mx.ipn.escom.complexsystems.microworld.definition.impl.WorldTypes

import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * Created by alberto on 28/09/15.
 */
public class DrawingPanel extends JPanel implements ActionListener {

    public final int DELAY = 500;
    public int rows = 0;
    public int columns = 0;
    public Timer timer;
    public MicroWorldAutomata automata;

    public DrawingPanel(MicroWorldAutomata automata) {
        this.automata = automata
        rows = automata?.rows;
        columns = automata.columns;
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
    }

    public Timer getTimer() {

        return timer;
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        if (automata.generation == 0 && automata.start == true) {
            this.drawEntireMap(g2d);
            automata.start = false;
            return;
        } else {
            this.drawEntireMap(g2d);
        }


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    // timer!
    @Override
    public void actionPerformed(ActionEvent e) {
        automata.task();
        repaint();
    }

    public void drawEntireMap(Graphics2D g2d) {
        automata.currentElements.Water.each { water ->
            g2d.setPaint(Color.BLUE);
            g2d.drawLine(water.position[0], water.position[1], water.position[0], water.position[1]);
        }
        automata.currentElements.Plant.each { plant ->
            g2d.setPaint(Color.GREEN);
            g2d.drawLine(plant.position[0], plant.position[1], plant.position[0], plant.position[1]);
        }
        automata.currentElements.Ground.each { ground ->
            g2d.setPaint(Color.YELLOW);
            g2d.drawLine(ground.position[0], ground.position[1], ground.position[0], ground.position[1]);
        }
        automata.currentElements.Herbivore.each { herbivore ->
            g2d.setPaint(Color.CYAN);
            g2d.drawLine(herbivore.position[0], herbivore.position[1], herbivore.position[0], herbivore.position[1]);
        }
        automata.currentElements.Carnivore.each { carnivore ->
            g2d.setPaint(Color.RED);
            g2d.drawLine(carnivore.position[0], carnivore.position[1], carnivore.position[0], carnivore.position[1]);
        }
        automata.currentElements.Scavenger.each { scavenger ->
            g2d.setPaint(Color.ORANGE);
            g2d.drawLine(scavenger.position[0], scavenger.position[1], scavenger.position[0], scavenger.position[1]);
        }
        automata.currentElements.Corpse.each { corpse ->
            g2d.setPaint(Color.BLACK);
            g2d.drawLine(corpse.position[0], corpse.position[1], corpse.position[0], corpse.position[1]);
        }

    }

}