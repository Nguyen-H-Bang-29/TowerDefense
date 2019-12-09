package Entities.Bullets;

import Entities.Critters.Critter;
import Entities.Towers.Tower;

public class BasicBullet extends Bullet {
    public BasicBullet(Tower source, Critter target) {
        super(source, target);
        speed = 15;
        damage = 100;
        img = "graphics/BasicBullet.png";
    }
}
