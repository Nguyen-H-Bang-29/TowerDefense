package Entities.Critters;

public class Critter3 extends Critter {
    public Critter3(int xEntrance, int yEntrance) {
        this.health = 800;
        this.xPos = xEntrance * 75;
        this.yPos = yEntrance * 75;
        this.speed = 4;
        this.damage = 10;
        this.reward = 10;
        this.img = "graphics/Enemy31.png";
        this.southImg1 = "graphics/Enemy31.png";
        this.southImg3 = "graphics/Enemy33.png";
        this.eastImg2 = "graphics/Enemy32.png";
        this.eastImg4 = "graphics/Enemy34.png";
        this.frozenImg1 = "graphics/frozenEnemy31.png";
        this.frozenImg2 = "graphics/frozenEnemy32.png";
        this.frozenImg3 = "graphics/frozenEnemy33.png";
        this.frozenImg4 = "graphics/frozenEnemy34.png";
        this.theta = Math.PI / 2;
        this.isAlive = true;
    }

    @Override
    public void update() {
        super.update();
    }
}
