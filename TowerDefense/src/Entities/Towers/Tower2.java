package Entities.Towers;

import Entities.Bullets.Bullet2;
import Entities.Model;

public class Tower2 extends Tower {
    public Tower2(int x, int y) {
        this.xTilePos = x;
        this.yTilePos = y;
        this.price = 75;
        this.damage = 150;
        this.reloadTime = 1.5;
        this.lastShotTime = 0;
        this.range = 200;
        this.img = "graphics/Tower2.png";
    }

    @Override
    public void attackTarget() {
        if ((System.currentTimeMillis() - lastShotTime) / 1000 > reloadTime) {
            lastShotTime = System.currentTimeMillis();
            Model.bullets.add(new Bullet2(this, target));
            Model.addSound("sound/Bullet2.wav");
        }
    }

    @Override
    public void update() {
        super.update();
    }
}
