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

    public final int DELAY = 400;
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
        for (short row = 0; row < automata.rows; row++) {
            for (short column = 0; column < automata.columns; column++) {
                switch (automata.world[row][column].type) {
                    case WorldTypes.Plant.value:
                        g2d.setPaint(Color.GREEN);
                        g2d.drawLine(row, column, row, column);
                        break;
                    case WorldTypes.Water.value:
                        g2d.setPaint(Color.BLUE);
                        g2d.drawLine(row, column, row, column);
                        break;
                    case WorldTypes.Ground.value:
                        g2d.setPaint(Color.YELLOW);
                        g2d.drawLine(row, column, row, column);
                        break;
                    case WorldTypes.Herbivore.value:
                        g2d.setPaint(Color.CYAN);
                        g2d.drawLine(row, column, row, column);
                        break;
                    case WorldTypes.Carnivore.value:
                        g2d.setPaint(Color.red);
                        g2d.drawLine(row, column, row, column);
                        break;
                    case WorldTypes.Scavenger.value:
                        g2d.setPaint(Color.ORANGE);
                        g2d.drawLine(row, column, row, column);
                        break;
                    case WorldTypes.Corpse.value:
                        g2d.setPaint(Color.BLACK);
                        g2d.drawLine(row, column, row, column);
                        break;
                }

            }
        }
    }

}