package MainPanels;

import Entities.Model;
import Entities.Towers.BasicTower;
import Entities.Towers.Tower;
import Entities.Towers.Tower2;
import Entities.Towers.Tower3;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private JLabel goldOwned;
    private JLabel goldIcon;
    private JLabel basicTowerIcon, freezerTowerIcon, sniperTowerIcon;
    private JLabel basicTowerDes, freezerTowerDes, sniperTowerDes;
    private JLabel score;
    private JLabel healthBar;
    private JLabel sellTower;

    public Model.towerTypes selectedTower = null;
    public int n = 0;

    public GamePanel(){
        goldIcon = Model.loadIconLabel("graphics/CurrencyGraphic.png", 50, 50, 30, 30);
        goldOwned = Model.loadTextLabel(Model.gold + "$", 95, 50, 130, 30);
        goldOwned.setFont(goldOwned.getFont().deriveFont((float) 20));

        healthBar = Model.loadIconLabel("graphics/HealthBar.png", 20, 140, Model.health/100*150, 20);

        basicTowerIcon = Model.loadIconLabel("graphics/BasicTower.png", 10,200, 75, 75);
        freezerTowerIcon =  Model.loadIconLabel("graphics/Tower2.png", 10, 300, 75, 75);
        sniperTowerIcon =  Model.loadIconLabel("graphics/Tower3.png", 10, 400, 75, 75);

        basicTowerDes = Model.loadTextLabel( "<html>Cost: 50$<br>Range: 175<br>Damage: 100<br>Reload: 0.5s</html>" ,90, 200, 75, 75);
        freezerTowerDes = Model.loadTextLabel("<html>Cost: 75$<br>Range: 200<br>Damage: 150<br>Reload: 1.5s</html>", 90, 300, 75, 75);
        sniperTowerDes = Model.loadTextLabel("<html>Cost: 100$<br>Range: 230<br>Damage: 200<br>Reload: 2.5s</html>", 90, 400, 75, 75);

        score = Model.loadTextLabel("Score: " + Model.score, 50, 480, 150 , 20);
        score.setFont(score.getFont().deriveFont((float) 20));

        sellTower = Model.loadIconLabel("graphics/shovel.png", 55, 520, 75, 75);

        this.setSize(200, 600);
        this.setPreferredSize(new Dimension(200, 600));
        this.setLocation(600, 0);
        this.setLayout(null);

        repaint();
        this.setVisible(true);
    }

    public void updateGold(int goldEarned){
        Model.gold += goldEarned;
        goldOwned = Model.loadTextLabel(Model.gold + "$", 95, 50, 130, 30);
        goldOwned.setFont(goldOwned.getFont().deriveFont((float) 20));
        revalidate();
        repaint();
    }

    public void earnScore(int scoreEarned){
        this.remove(this.score);
        Model.score += scoreEarned;
        this.score = Model.loadTextLabel("Score: " + Model.score, 50, 480, 150 , 20);
        this.score.setFont(this.score.getFont().deriveFont((float) 20));
        revalidate();
        repaint();
    }

    public void selectTower(Model.towerTypes selectedTower){
        this.selectedTower = selectedTower;
        Model.addSound("sound/Click.wav");
        switch (selectedTower){
            case basic:
                basicTowerIcon = Model.loadIconLabel("graphics/BasicTowerSelected.png", 10,200, 75, 75);
                break;
            case freezer:
                freezerTowerIcon =  Model.loadIconLabel("graphics/Tower2Selected.png", 10, 300, 75, 75);
                break;
            case sniper:
                sniperTowerIcon =  Model.loadIconLabel("graphics/Tower3Selected.png", 10, 400, 75, 75);
                break;
            case shovel:
                sellTower = Model.loadIconLabel("graphics/shovelSelected.png", 55, 520,75,75);
                break;
            default:
        }
        revalidate();
        repaint();
    }

    public void unselectTower(){
        if(selectedTower == null) return;
        switch (selectedTower){
            case basic:
                basicTowerIcon = Model.loadIconLabel("graphics/BasicTower.png", 10,200, 75, 75);
                break;
            case freezer:
                freezerTowerIcon =  Model.loadIconLabel("graphics/Tower2.png", 10, 300, 75, 75);
                break;
            case sniper:
                sniperTowerIcon =  Model.loadIconLabel("graphics/Tower3.png", 10, 400, 75, 75);
                break;
            case shovel:
                sellTower = Model.loadIconLabel("graphics/shovel.png", 55, 520, 75, 75);
            default:
        }
        this.selectedTower = null;
        revalidate();
        repaint();
    }

    public void loseHealth(int healthLost){
        Model.health -= healthLost;
        healthBar = Model.loadIconLabel("graphics/HealthBar.png", 20, 140, (int) (Model.health*1.5), 20);
        revalidate();
        repaint();
    }

    public void buyTower(int xTilePos, int yTilePos){
        Tower tower;
        switch (selectedTower){
            case basic:
                tower = new BasicTower(xTilePos, yTilePos);
                n = 1;
                break;
            case freezer:
                tower = new Tower2(xTilePos, yTilePos);
                n = 2;
                break;
            case sniper:
                tower = new Tower3(xTilePos, yTilePos);
                n = 3;
                break;
            default:
                tower = null;
        }
        unselectTower();
        if(tower != null && Model.gold >= tower.price && Model.mapMatrix[yTilePos][xTilePos] == 0) {
            Model.mapMatrix[yTilePos][xTilePos] = 2;
            Model.towers.add(tower);
            updateGold(-tower.price);
        }
    }
    public void sellTower(int xTilePos, int yTilePos){
        if (selectedTower == Model.towerTypes.shovel) {
            for (int i=0; i<Model.towers.size(); i++){
                if (xTilePos == Model.towers.get(i).xTilePos && yTilePos == Model.towers.get(i).yTilePos){
                    Model.towers.remove(Model.towers.get(i));
                }
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.removeAll();

        this.add(goldIcon);
        this.add(goldOwned);
        this.add(healthBar);
        this.add(basicTowerIcon);
        this.add(freezerTowerIcon);
        this.add(sniperTowerIcon);
        this.add(basicTowerDes);
        this.add(freezerTowerDes);
        this.add(sniperTowerDes);
        this.add(score);
        this.add(sellTower);

        this.setVisible(true);
    }
}
