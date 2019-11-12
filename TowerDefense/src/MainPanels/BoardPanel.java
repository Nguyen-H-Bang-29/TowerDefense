package MainPanels;

import Entities.Bullets.Bullet;
import Entities.Critters.Critter;
import Entities.Model;
import Entities.Towers.BasicTower;
import Entities.Towers.Tower;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {

    public BoardPanel(int[][] mapMatrix, int xEntrance, int yEntrance, int xExit, int yExit) {
        Model.mapRenderMatrix = new Image[8][8];
        Model.mapMatrix = mapMatrix;
        Model.xEntrance = xEntrance;
        Model.yEntrance = yEntrance;
        Model.xExit = xExit;
        Model.yExit = yExit;

        this.setSize(600, 600);
        this.setPreferredSize(new Dimension(600, 600));
        this.setLocation(0, 0);
        this.setLayout(new GridLayout(8,8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Model.mapMatrix[i][j] == 0) {
                    Model.mapRenderMatrix[i][j] = Model.loadImage("graphics/SandTile.png");
                } else {
                    Model.mapRenderMatrix[i][j] = Model.loadImage("graphics/BluePathTileGraphic.png");
                }
            }
        }

        repaint();
    }

    private void loadMap(Graphics g) {
        removeAll();
        revalidate();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g.drawImage(Model.mapRenderMatrix[i][j], 75*j, 75*i, 75,75 ,null);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        loadMap(g2d);

        for (Critter critter : Model.critters) {
            g2d.drawImage(Model.loadImage(critter.img), critter.xPos, critter.yPos, 75, 75, null);
        }
        for (Bullet bullet : Model.bullets) {
            g2d.drawImage(Model.loadImage(bullet.img), bullet.xPos, bullet.yPos, 10, 10, null);
        }
        for (Tower tower : Model.towers) {
            g2d.drawImage(Model.loadImage(tower.img), tower.xTilePos * 75, tower.yTilePos * 75, 75, 75, null);
        }
    }
}
