package Entities.Critters;

public class Critter2 extends Critter {
    public Critter2(int xEntrance, int yEntrance) {
        this.health = 750;
        this.xPos = xEntrance * 75;
        this.yPos = yEntrance * 75;
        this.speed = 3;
        this.damage = 10;
        this.reward = 7;
        this.img = "graphics/Enemy21.png";
        this.southImg1 = "graphics/Enemy21.png";
        this.southImg3 = "graphics/Enemy23.png";
        this.eastImg2 = "graphics/Enemy22.png";
        this.eastImg4 = "graphics/Enemy24.png";
        this.frozenImg1 = "graphics/frozenEnemy21.png";
        this.frozenImg2 = "graphics/frozenEnemy22.png";
        this.frozenImg3 = "graphics/frozenEnemy23.png";
        this.frozenImg4 ="graphics/frozenEnemy24.png";
        this.theta = Math.PI / 2;
        this.isAlive = true;
    }

    @Override
    public void update() {
        super.update();
    }
}
