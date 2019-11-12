import Entities.Bullets.Bullet;
import Entities.Critters.BasicCritter;
import Entities.Critters.Critter;
import Entities.Model;
import Entities.Towers.Tower;
import MainPanels.BoardPanel;
import MainPanels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame implements MouseListener {
    boolean isRunning = true;

    GamePanel gamePanel ;
    BoardPanel boardPanel;

    public Window(){
        gamePanel = new GamePanel();
        boardPanel = new BoardPanel(new int[][]{
                {0, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 1, 1, 1, 0},
                {0, 0, 1, 0, 1, 0, 0, 0},
                {1, 1, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 0}
        }, 0, 4, 0, 6);
        this.setSize(800, 638);
        this.setLayout(null);
        this.add(gamePanel);
        this.add(boardPanel);
        this.setVisible(true);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() - 7;
        int y = e.getY() - 30;

        if(x > 610 && x < 685 && y > 200 && y < 275) {
            if (gamePanel.selectedTower != null) gamePanel.unselectTower();
            gamePanel.selectTower(Model.towerTypes.basic);
        }
        else if(x > 610 && x < 685 && y > 300 && y < 375) {
            if (gamePanel.selectedTower != null) gamePanel.unselectTower();
            gamePanel.selectTower(Model.towerTypes.freezer);
        }
        else if(x > 610 && x < 685 && y > 400 && y < 475) {
            if (gamePanel.selectedTower != null) gamePanel.unselectTower();
            gamePanel.selectTower(Model.towerTypes.sniper);
        }

        else if(x < 600 && x > 0 && y < 600 && y > 0 && gamePanel.selectedTower != null){
            gamePanel.buyTower(x/75, y/75);
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
                        JDialog lostDialog = new JDialog();
                        JLabel message = new JLabel("You lost!");
                        lostDialog.add(message);
                        isRunning = false;
                        lostDialog.setDefaultCloseOperation(HIDE_ON_CLOSE);
                        lostDialog.setBounds(300, 250, 200, 100);
                        lostDialog.setVisible(true);
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
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gamePanel.loseHealth(5);
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
