package Entities.Critters;

public class BasicCritter extends Critter {
    public BasicCritter(int xEntrance, int yEntrance) {
        this.health = 650;
        this.xPos = xEntrance * 75;
        this.yPos = yEntrance * 75;
        this.speed = 3;
        this.damage = 5;
        this.reward = 5;
        this.img = "graphics/BasicEnemy1.png";
        this.eastImg2 = "graphics/BasicEnemy2.png";
        this.eastImg4 = "graphics/BasicEnemy4.png";
        this.southImg1 = "graphics/BasicEnemy1.png";
        this.southImg3 = "graphics/BasicEnemy3.png";
        this.frozenImg1 = "graphics/frozenBasicEnemy1.png";
        this.frozenImg2 = "graphics/frozenBasicEnemy2.png";
        this.frozenImg3 = "graphics/frozenBasicEnemy3.png";
        this.frozenImg4 = "graphics/frozenBasicEnemy4.png";
        this.theta = Math.PI / 2;
        this.isAlive = true;
    }

    @Override
    public void update() {
        super.update();
    }
}
