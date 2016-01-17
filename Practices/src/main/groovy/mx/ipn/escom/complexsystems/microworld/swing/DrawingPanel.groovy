package mx.ipn.escom.complexsystems.microworld.swing

import mx.ipn.escom.complexsystems.microworld.definition.impl.MicroWorldAutomata

import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * Created by alberto on 28/09/15.
 */
public class DrawingPanel extends JPanel implements ActionListener {

    public final int DELAY = 8000;
    public int rows = 0;
    public int columns = 0;
    public Timer timer;
    public MicroWorldAutomata automata;
    //Labels Copies
    JLabel herbivoresLabel
    JLabel carnivoresLabel
    JLabel plantsLabel
    JLabel scavengersLabel
    JLabel corpsesLabel
    JLabel generationLabel
    //Colors
    public Color water = new Color(32, 178, 170) // 20B2AA
    public Color ground = new Color(255, 222, 173) // FFDEAD
    public Color plant = new Color(0, 100, 0) // 006400
    public Color carnivore = new Color(165, 42, 42) // A52A2A
    public Color herbivore = new Color(47, 79, 79) // 2F4F4F
    public Color scavenger = new Color(210, 105, 30) // D2691E
    public Color corpse = new Color(189, 183, 107) // BDB76B


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

        this.herbivoresLabel.setText("$automata.statistics.Herbivore")
        this.carnivoresLabel.setText("$automata.statistics.Carnivore")
        this.scavengersLabel.setText("$automata.statistics.Scavenger")
        this.corpsesLabel.setText("$automata.statistics.Scavenger")
        this.plantsLabel.setText("$automata.statistics.Plant")
        this.generationLabel.setText("$automata.generation")
        repaint();
    }

    public void drawEntireMap(Graphics2D g2d) {
        this.setBackground(this.water)
        int posX = 0;
        int posY = 0;
        g2d.scale(2.0, 2.0)
        automata.currentElements.Plant.each { element ->
            g2d.setPaint(this.plant);
            posX = element.position[0]
            posY = element.position[1]
            g2d.drawLine(posX, posY, posX, posY);

        }
        automata.currentElements.Ground.each { element ->
            g2d.setPaint(this.ground);
            posX = element.position[0]
            posY = element.position[1]
            g2d.drawLine(posX, posY, posX, posY);
        }
        automata.currentElements.Herbivore.each { element ->
            g2d.setPaint(this.herbivore);
            posX = element.position[0]
            posY = element.position[1]
            g2d.drawLine(posX, posY, posX, posY);
        }
        automata.currentElements.Carnivore.each { element ->
            g2d.setPaint(this.carnivore);
            posX = element.position[0]
            posY = element.position[1]
            g2d.drawLine(posX, posY, posX, posY);
        }
        automata.currentElements.Scavenger.each { element ->
            g2d.setPaint(this.scavenger);
            posX = element.position[0]
            posY = element.position[1]
            g2d.drawLine(posX, posY, posX, posY);
        }
        automata.currentElements.Corpse.each { element ->
            g2d.setPaint(this.corpse);
            posX = element.position[0]
            posY = element.position[1]
            g2d.drawLine(posX, posY, posX, posY);
        }

    }

}