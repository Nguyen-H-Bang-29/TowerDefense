package Entities.Bullets;

import Entities.Critters.Critter;
import Entities.Towers.Tower;

public class Bullet3 extends Bullet {
    public Bullet3(Tower source, Critter target) {
        super(source, target);
        speed = 17;
        damage = 250;
        img = "graphics/Bullet3.png";
    }
}
