package Entities.Critters;

import Entities.Model;

public abstract class Critter {
    public int xPos, yPos;
    public boolean reachTarget = false;

    public int damage;
    public double speed;
    public int health;
    public double theta;
    public int reward;
    public String img;
    public int xTilePos;
    public int yTilePos;
    public boolean isAlive;
    public int stepCount = 0;
    public String eastImg2, eastImg4;
    public String southImg3, southImg1;
    public String frozenImg1, frozenImg2, frozenImg3, frozenImg4;
    public double timeShot;
    public boolean isFrozen = false;

    private void updatePos() {
        if (theta == Math.PI / 2) {
            xPos += speed;
            
            xTilePos = xPos / 75;
            yTilePos = yPos / 75;
        } // east
        else if (theta == Math.PI) {
            yPos += speed;
            xTilePos = xPos / 75;
            yTilePos = yPos / 75;
        } // south
        else if (theta == -Math.PI / 2) {
            xPos -= speed;
            xTilePos = xPos / 75 + 1;
            yTilePos = yPos / 75;
            if (xPos < 0) xTilePos = 0;
        } // west
        else if (theta == 0) {
            yPos -= speed;
            xTilePos = xPos / 75;
            yTilePos = yPos / 75 + 1;
            if (yPos < 0) yTilePos = 0;
        } // north
        stepCount += speed;
    }

    private boolean isColliding() {
        if (theta == Math.PI / 2) {
            if (xTilePos >= 7) return true;
            if (Model.mapMatrix[yTilePos][xTilePos + 1] != 1) return true;
        } // east
        else if (theta == Math.PI) {
            if (yTilePos >= 7) return true;
            if (Model.mapMatrix[yTilePos + 1][xTilePos] != 1) return true;
        } // south
        else if (theta == -Math.PI / 2) {
            if (xTilePos <= 0) return true;
            if (Model.mapMatrix[yTilePos][xTilePos - 1] != 1) return true;
        } // west
        else if (theta == 0) {
            if (yTilePos <= 0) return true;
            if (Model.mapMatrix[yTilePos - 1][xTilePos] != 1) return true;
        } // north
        return false;
    }

    private void handleCollision() {
        if (xTilePos == Model.xExit && yTilePos == Model.yExit) {
            isAlive = false;
            reachTarget = true;
            return;
        }
        if (theta == Math.PI / 2) {
            if (yTilePos < 7)
                if (Model.mapMatrix[yTilePos + 1][xTilePos] == 1) {
                    theta = Math.PI;
                    img = eastImg2;
                }
            if (yTilePos > 0)
                if (Model.mapMatrix[yTilePos - 1][xTilePos] == 1) {
                    theta = 0;
                    img = eastImg4;
                }
        } // east
        else if (theta == Math.PI) {
            if (xTilePos > 0)
                if (Model.mapMatrix[yTilePos][xTilePos - 1] == 1) {
                    theta = -Math.PI / 2;
                    img = southImg3;
                }
            if (xTilePos < 7)
                if (Model.mapMatrix[yTilePos][xTilePos + 1] == 1) {
                    theta = Math.PI / 2;
                    img = southImg1;
                }
        } // south
        else if (theta == -Math.PI / 2) {
            if (yTilePos < 7)
                if (Model.mapMatrix[yTilePos + 1][xTilePos] == 1) {
                    theta = Math.PI;
                    img = eastImg2;
                }
            if (yTilePos > 0)
                if (Model.mapMatrix[yTilePos - 1][xTilePos] == 1) {
                    theta = 0;
                    img = eastImg4;
                }
        } // west
        else if (theta == 0) {
            if (xTilePos > 0)
                if (Model.mapMatrix[yTilePos][xTilePos - 1] == 1) {
                    theta = -Math.PI / 2;
                    img = southImg3;
                }
            if (xTilePos < 7)
                if (Model.mapMatrix[yTilePos][xTilePos + 1] == 1) {
                    theta = Math.PI / 2;
                    img = southImg1;
                }
        } // north
        xPos = xTilePos * 75;
        yPos = yTilePos * 75;
    }

    public void updateHealth() {
        if (health <= 0) isAlive = false;
    }

    public void update() {
        updatePos();
        updateHealth();
        if (isFrozen) {
            if(System.currentTimeMillis() - timeShot > 700){
                isFrozen = false;
                speed *= 2;
            }
        }
        if (isColliding()) handleCollision();
    }
}
