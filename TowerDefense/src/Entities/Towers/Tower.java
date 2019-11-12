package Entities.Towers;

import Entities.Critters.Critter;
import Entities.Model;

abstract public class Tower {
    public int xTilePos;
    public int yTilePos;
    public int price;
    public double reloadTime, lastShotTime;
    public int range;
    public int damage;
    public String img;
    public boolean hasTarget;
    public Critter target;

    public double getDistance(Critter critter) {
        return Math.sqrt((xTilePos * 75 - critter.xPos) * (xTilePos * 75 - critter.xPos) + (yTilePos * 75 - critter.yPos) * (yTilePos * 75 - critter.yPos));
    }

    public void findTarget() {
        target = null;
        int maxStepCount = 0;
        for (Critter critter : Model.critters) {
            if (getDistance(critter) < range && critter.stepCount > maxStepCount) {
                target = critter;
                maxStepCount = critter.stepCount;
                hasTarget = true;
            }
        }
    }

    public abstract void attackTarget();

    public void update() {
        if (hasTarget) {
            attackTarget();
            if (!target.isAlive || getDistance(target) > range) {
                target = null;
                hasTarget = false;
            }
        } else {
            findTarget();
        }
    }
}
