package MainPanels;

import Entities.Model;

import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel{
    public JLabel restartIcon;
    public EndPanel(){
        setSize(800, 600);
        restartIcon = Model.loadIconLabel("graphics/restartButton.png", 260, 400,290, 100 );
    }

    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Image endImage = Model.loadImage("graphics/endGame.png");
        g2d.drawImage(endImage,0,0, 800, 620, null);

        g2d.setFont(new Font("VNI-Whimsy", Font.BOLD, 70));
        g2d.setColor(new Color(46,87,127));
        g2d.drawString("" + Model.score, 500, 200);

        this.add(restartIcon);

    }
}
