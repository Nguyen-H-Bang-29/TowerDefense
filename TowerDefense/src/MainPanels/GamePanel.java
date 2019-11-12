package MainPanels;

import Entities.Model;
import Entities.Towers.BasicTower;
import Entities.Towers.Tower;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private JLabel goldOwned;
    private JLabel goldIcon;
    private JLabel basicTowerIcon, freezerTowerIcon, sniperTowerIcon;
    private JLabel basicTowerDes, freezerTowerDes, sniperTowerDes;
    private JLabel score;
    private JLabel healthBar;

    public Model.towerTypes selectedTower = null;

    public GamePanel(){
        goldIcon = Model.loadIconLabel("graphics/CurrencyGraphic.png", 50, 50, 30, 30);
        goldOwned = Model.loadTextLabel(Model.gold + "G", 95, 50, 130, 30);
        goldOwned.setFont(goldOwned.getFont().deriveFont((float) 20));

        healthBar = Model.loadIconLabel("graphics/HealthBar.png", 20, 140, Model.health/100*150, 20);

        basicTowerIcon = Model.loadIconLabel("graphics/BasicTower.png", 10,200, 75, 75);
        freezerTowerIcon =  Model.loadIconLabel("graphics/BasicTower.png", 10, 300, 75, 75);
        sniperTowerIcon =  Model.loadIconLabel("graphics/BasicTower.png", 10, 400, 75, 75);

        basicTowerDes = Model.loadTextLabel( "<html>Cost: 50G<br>Range: 200<br>Damage: 100<br>Reload: 0.5s</html>" ,90, 200, 75, 75);
        freezerTowerDes = Model.loadTextLabel("<html>Cost: 50G<br>Range: 200<br>Damage: 100<br>Reload: 0.5s</html>", 90, 300, 75, 75);
        sniperTowerDes = Model.loadTextLabel("<html>Cost: 50G<br>Range: 200<br>Damage: 100<br>Reload: 0.5s</html>", 90, 400, 75, 75);

        score = Model.loadTextLabel("Score: " + Model.score, 50, 510, 150 , 30);
        score.setFont(score.getFont().deriveFont((float) 20));

        this.setSize(200, 600);
        this.setPreferredSize(new Dimension(200, 600));
        this.setLocation(600, 0);
        this.setLayout(null);

        repaint();
        this.setVisible(true);
    }

    public void updateGold(int goldEarned){
        Model.gold += goldEarned;
        goldOwned = Model.loadTextLabel(Model.gold + "G", 95, 50, 130, 30);
        goldOwned.setFont(goldOwned.getFont().deriveFont((float) 20));
        revalidate();
        repaint();
    }

    public void earnScore(int scoreEarned){
        this.remove(this.score);
        Model.score += scoreEarned;
        this.score = Model.loadTextLabel("Score: " + Model.score, 50, 510, 150 , 30);
        this.score.setFont(this.score.getFont().deriveFont((float) 20));
        revalidate();
        repaint();
    }

    public void selectTower(Model.towerTypes selectedTower){
        this.selectedTower = selectedTower;
        switch (selectedTower){
            case basic:
                basicTowerIcon = Model.loadIconLabel("graphics/BrickTile.png", 10,200, 75, 75);
                break;
            case freezer:
                freezerTowerIcon =  Model.loadIconLabel("graphics/BrickTile.png", 10, 300, 75, 75);
                break;
            case sniper:
                sniperTowerIcon =  Model.loadIconLabel("graphics/BrickTile.png", 10, 400, 75, 75);
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
                freezerTowerIcon =  Model.loadIconLabel("graphics/BasicTower.png", 10, 300, 75, 75);
                break;
            case sniper:
                sniperTowerIcon =  Model.loadIconLabel("graphics/BasicTower.png", 10, 400, 75, 75);
                break;
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
                break;
            case freezer:
                tower = null;
                break;
            case sniper:
                tower = null;
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

        this.setVisible(true);
    }
}
