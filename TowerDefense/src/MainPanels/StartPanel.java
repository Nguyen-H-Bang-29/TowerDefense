package MainPanels;

import Entities.Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class StartPanel extends JPanel {
    int n = 0;
    public Clip clip;
    boolean hasSound=false;
    AudioInputStream  audioInputStream;
    // AudioInputStream audioInputStream;

    public StartPanel() {
        setSize(800, 600);
        ActionListener critterTListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        };

        Timer critterTimer = new Timer(300, critterTListener);
        critterTimer.setRepeats(true);
        critterTimer.start();
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        switch (n%2) {

            case 0:
                Image image1 = Model.loadImage("graphics/scrpng1.png");
                g2d.drawImage(image1, 0, 0, 800, 620, null);
                n++;
                break;
            case 1:
                Image image2 = Model.loadImage("graphics/scrpng.png");
                g2d.drawImage(image2, 0, 0, 800, 620, null);
                n++;
                break;
        }
    }
}