package Entities.Bullets;

import Entities.Critters.Critter;
import Entities.Model;
import Entities.Towers.Tower;

import java.awt.*;
import java.awt.geom.AffineTransform;

abstract public class Bullet {
    public Critter target;
    public int xT, yT, xS, yS;
    public Tower source;
    public int xPos, yPos;
    public int speed;
    public String img;
    public int damage;
    public double timeShot;
    public boolean isOnAir;
    public boolean isFreezer = false;

    Bullet(Tower source, Critter target) {
        this.target = target;
        this.source = source;
        this.xPos = (int) ((source.xTilePos + 0.5) * 75);
        this.yPos = (int) ((source.yTilePos + 0.5) * 75);
        this.xT = target.xPos;
        this.yT = target.yPos;
        this.xS = xPos;
        this.yS = yPos;
        timeShot = System.currentTimeMillis();
        isOnAir = true;
    }

    public void hitTarget() {
        target.timeShot = System.currentTimeMillis();
        if(isFreezer) {
            if(!target.isFrozen) {
                target.isFrozen = true;
                target.speed /= 2;

                if (target.theta == Math.PI / 2) {
                    target.img = target.frozenImg1;
                } // east
                else if (target.theta == Math.PI) {
                    target.img = target.frozenImg2;
                } // south
                else if (target.theta == -Math.PI / 2) {
                    target.img = target.frozenImg3;
                } // west
                else if (target.theta == 0) {
                    target.img = target.frozenImg4;
                } // north
            }
        }
        target.health -= damage;
        isOnAir = false;
    }

    public void update() {
        xPos += (double) speed * (xT + 75 / 2 - xS) / source.getDistance(target);
        yPos += (double) speed * (yT + 75 / 2 - yS) / source.getDistance(target);

        if (Math.abs(xPos - xT-75/2) < 25 && Math.abs(yPos - yT-75/2) < 25) hitTarget();
        else if(System.currentTimeMillis() - timeShot > 2000) isOnAir = false;
    }
}
