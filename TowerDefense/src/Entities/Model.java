package Entities;

import Entities.Bullets.Bullet;
import Entities.Critters.Critter;
import Entities.Towers.Tower;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Model {
    public static int[][] mapMatrix = new int[8][8];
    public static Image[][] mapRenderMatrix = new Image[8][8];
    public static int xEntrance, yEntrance, xExit, yExit;
    public static List<Tower> towers = new ArrayList<>();
    public static List<Critter> critters = new ArrayList<>();
    public static List<Bullet> bullets = new ArrayList<>();

    public enum towerTypes{
        basic,
        freezer,
        sniper
    }
    public static Image loadImage(String path) {
        Image img;
        try {
            img = ImageIO.read(new File(path));
            return img;
        } catch (IOException e) {
            return null;
        }
    }

    public static JLabel loadTextLabel (String text, int x, int y, int width, int height){
        JLabel label = new JLabel(text);
        label.setSize(width, height);
        label.setPreferredSize(new Dimension(width, height));
        label.setLocation(x, y);
        return label;
    }
    public static JLabel loadIconLabel(String path, int x, int y, int width, int height){
        JLabel label = new JLabel(new ImageIcon(path));
        label.setSize(width, height);
        label.setPreferredSize(new Dimension(width, height));
        label.setLocation(x, y);
        return label;
    }

    public static int health = 100;
    public static int gold = 100;
    public static int score = 0;
    public static double lastUpdateGold;

}
