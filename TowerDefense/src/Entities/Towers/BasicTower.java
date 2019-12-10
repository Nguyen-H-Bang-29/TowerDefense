package Entities.Towers;

import Entities.Bullets.BasicBullet;
import Entities.Model;

public class BasicTower extends Tower {
    public BasicTower(int x, int y) {
        this.xTilePos = x;
        this.yTilePos = y;
        this.price = 50;
        this.damage = 100;
        this.reloadTime = 0.5;
        this.lastShotTime = 0;
        this.range = 200;
        this.img = "graphics/BasicTower.png";
    }

    @Override
    public void attackTarget() {
        if ((System.currentTimeMillis() - lastShotTime) / 1000 > reloadTime) {
            lastShotTime = System.currentTimeMillis();
            Model.bullets.add(new BasicBullet(this, target));
            Model.addSound("sound/Bullet1.wav");
        }
    }

    @Override
    public void update() {
        super.update();
    }
}
