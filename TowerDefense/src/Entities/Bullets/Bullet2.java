package Entities.Bullets;

import Entities.Critters.Critter;
import Entities.Towers.Tower;

public class Bullet2 extends Bullet {
    public Bullet2(Tower source, Critter target) {
        super(source, target);
        speed = 15;
        damage = 175;
        img = "graphics/Bullet2.png";
        this.isFreezer = true;
    }
}
