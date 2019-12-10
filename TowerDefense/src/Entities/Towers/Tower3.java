package Entities.Towers;

import Entities.Bullets.BasicBullet;
import Entities.Bullets.Bullet2;
import Entities.Bullets.Bullet3;
import Entities.Model;

import java.rmi.MarshalledObject;

public class Tower3 extends Tower {
    public Tower3(int x, int y) {
        this.xTilePos = x;
        this.yTilePos = y;
        this.price = 100;
        this.damage = 200;
        this.reloadTime = 2.5;
        this.lastShotTime = 0;
        this.range = 250;
        this.img = "graphics/Tower3.png";
    }

    @Override
    public void attackTarget() {
        if ((System.currentTimeMillis() - lastShotTime) / 1000 > reloadTime) {
            lastShotTime = System.currentTimeMillis();
            Model.bullets.add(new Bullet3(this, target));
            Model.addSound("sound/Bullet3.wav");
        }
    }

    @Override
    public void update() {
        super.update();
    }
}
