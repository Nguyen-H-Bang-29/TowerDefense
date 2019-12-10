import Entities.Bullets.Bullet;
import Entities.Critters.BasicCritter;
import Entities.Critters.Critter;
import Entities.Critters.Critter2;
import Entities.Critters.Critter3;
import Entities.Model;
import Entities.Towers.Tower;
import MainPanels.BoardPanel;
import MainPanels.EndPanel;
import MainPanels.GamePanel;
import MainPanels.StartPanel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Window extends JFrame implements MouseListener {
    boolean isRunning = false;
    StartPanel startPanel;

    GamePanel gamePanel ;
    BoardPanel boardPanel;
    EndPanel endPanel;

    public Window(){
        startPanel = new StartPanel();
        endPanel = new EndPanel();

        gamePanel = new GamePanel();
        boardPanel = new BoardPanel(new int[][]{
                {0, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 3, 0, 1, 0},
                {4, 1, 1, 0, 1, 1, 1, 0},
                {0, 0, 1, 4, 1, 0, 3, 0},
                {1, 1, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 3, 1, 1, 4},
                {1, 1, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 0}
        }, 0, 4, 0, 6);
        this.setSize(800, 638);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.add(startPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gamePanel.loseHealth(5);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32){
                    getContentPane().removeAll();
                    getContentPane().repaint();
                    isRunning = true;

                    Model.stopSound();
                    Model.addSound("sound/SoundTrack.wav");
                    Model.loopSound();
                    add(gamePanel);
                    add(boardPanel);
                }
            }
        });
        this.setVisible(true);
        this.setTitle("Tower Defense");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() - 7;
        int y = e.getY() - 30;
        if(x > 610 && x < 685 && y > 200 && y < 275) {
            if (gamePanel.selectedTower != null) gamePanel.unselectTower();
            gamePanel.selectTower(Model.towerTypes.basic);
        } else if(x > 610 && x < 685 && y > 300 && y < 375) {
            if (gamePanel.selectedTower != null) gamePanel.unselectTower();
            gamePanel.selectTower(Model.towerTypes.freezer);
        } else if(x > 610 && x < 685 && y > 400 && y < 475) {
            if (gamePanel.selectedTower != null) gamePanel.unselectTower();
            gamePanel.selectTower(Model.towerTypes.sniper);
        } else if (x > 650 && x < 720 && y > 520 && y < 585){
            if (gamePanel.selectedTower != null) gamePanel.unselectTower();
            gamePanel.selectTower(Model.towerTypes.shovel);

        } else if(x < 600 && x > 0 && y < 600 && y > 0 && gamePanel.selectedTower != null){
            gamePanel.buyTower(x/75, y/75);
        } else if (x < 600 && x > 0 && y < 600 && y > 0){
            gamePanel.sellTower(x/75, y/75);
        } else if (x > 620 && x < 670 && y > 15 && y < 45){
            isRunning = false;
            Model.stopSound();
            getContentPane().removeAll();
            getContentPane().repaint();
            add(gamePanel);
            add(boardPanel);
        } else if (x > 695 && x < 780 && y > 15 && y < 45){
            isRunning = true;
            Model.addSound("sound/SoundTrack.wav");
            Model.loopSound();
        }
        else gamePanel.unselectTower();
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX() - 7;
        int y = e.getY() - 30;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX() - 7;
        int y = e.getY() - 30;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        int x = e.getX() - 7;
        int y = e.getY() - 30;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        int x = e.getX() - 7;
        int y = e.getY() - 30;
    }

    private void crittersUpdate() {
        List<Critter> removedList = new ArrayList<>();
        for (Critter critter : Model.critters) {
            critter.update();
            if (!critter.isAlive)
                if(!critter.reachTarget){
                    gamePanel.updateGold(critter.reward);
                    gamePanel.earnScore(critter.reward);
                    removedList.add(critter);
                }
                else{
                    gamePanel.loseHealth(critter.damage);
                    if(Model.health <= 0) {
                        Model.stopSound();
                        Model.addSound("sound/GameOver.wav");
                        isRunning = false;
                        getContentPane().removeAll();
                        getContentPane().repaint();
                        add(endPanel);

                        addMouseListener(new MouseAdapter() {
                            public void mousePressed(MouseEvent e){
                                int x = e.getX();
                                int y = e.getY();
                                if (265 < x && x < 540 && 410 < y && y < 480){
                                    getContentPane().removeAll();
                                    getContentPane().repaint();

                                    add(gamePanel);
                                    add(boardPanel);
                                }
                            }
                        });

                    }
                    removedList.add(critter);
                }
        }
        for (Critter critter : removedList) {
            Model.critters.remove(critter);
        }
    }

    private static void towersUpdate() {
        for (Tower tower : Model.towers) {
            tower.update();
        }
    }

    private void bulletsUpdate() {
        List<Bullet> removedList = new ArrayList<>();
        for (Bullet bullet : Model.bullets) {
            bullet.update();
            if (!bullet.isOnAir) removedList.add(bullet);
        }
        for (Bullet bullet : removedList) {
            Model.bullets.remove(bullet);
        }
    }
    private void updateEntities() {
        bulletsUpdate();
        crittersUpdate();
        towersUpdate();
    }
    public void play() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isRunning) {
                    updateEntities();
                    boardPanel.repaint();
                }
            }
        };
        Timer timer = new Timer(1000 / 60, actionListener);
        timer.setRepeats(true);
        timer.start();

        ActionListener critterTListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isRunning) {
                    Model.critters.add(new BasicCritter(Model.xEntrance, Model.yEntrance));
                    Random random = new Random();
                    switch (random.nextInt()%2){
                        case 1:
                            Model.critters.add(new Critter2(Model.xEntrance, Model.yEntrance));
                            break;
                        case 0:
                            Model.critters.add(new Critter3(Model.xEntrance, Model.yEntrance));
                            break;

                    }
                }
            }
        };
        Timer critterTimer = new Timer(2000, critterTListener);
        critterTimer.setRepeats(true);
        critterTimer.start();

        ActionListener goldListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isRunning) {
                    gamePanel.updateGold(2);
                }
            }
        };
        Timer goldTimer = new Timer(1000, goldListener);
        goldTimer.setRepeats(true);
        goldTimer.start();
    }
}
