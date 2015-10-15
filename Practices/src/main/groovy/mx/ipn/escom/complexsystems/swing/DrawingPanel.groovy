package mx.ipn.escom.complexsystems.swing

import mx.ipn.escom.complexsystems.engine.impl.Automata;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Created by alberto on 28/09/15.
 */
public class DrawingPanel extends JPanel implements ActionListener {

    public final int DELAY = 250;
    public int rows = 0;
    public int columns = 0;
    public Timer timer;
    public Automata automata;

    public DrawingPanel(Automata automata) {
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
        g2d.setBackground(Color.white);
        if (automata.generation == 0 && automata.start == true) {
            this.drawEntireNeighborhood(g2d);
            automata.start = false;
            return;
        }

        if (automata.generation == 1) {
            this.drawEntireNeighborhood(g2d);
            return;
        }

        if (automata.generation > 1) {
            this.drawAlive(g2d);
            this.drawDeath(g2d);
            return;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        automata.task();
        repaint();
    }

    public void drawEntireNeighborhood(Graphics2D g2d) {
        g2d.setPaint(Color.black);
        for (short row = 0; row < rows; row++) {
            for (short column = 0; column < columns; column++) {
                if (automata.neighborhood[row][column] == 1) {
                    g2d.drawLine(row, column, row, column);
                }
            }
        }
    }

    public void drawAlive(Graphics2D g2d) {
        g2d.setPaint(Color.black);
        for (int[] cell : automata.newAlive) {
            g2d.drawLine(cell[0], cell[1], cell[0], cell[1]);
        }
    }

    public void drawDeath(Graphics2D g2d) {
        g2d.setPaint(Color.white);
        for (int[] cell : automata.newDeath) {
            g2d.drawLine(cell[0], cell[1], cell[0], cell[1]);
        }
    }
}