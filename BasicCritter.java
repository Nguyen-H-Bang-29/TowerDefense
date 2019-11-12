package Entities.Critters;

public class BasicCritter extends Critter {
    public BasicCritter(int xEntrance, int yEntrance) {
        this.health = 750;
        this.xPos = xEntrance * 75;
        this.yPos = yEntrance * 75;
        this.speed = 3;
        this.damage = 5;
        this.reward = 10;
        this.img = "graphics/ScorpionSpriteSheet.png";
        this.theta = Math.PI / 2;
        this.isAlive = true;
    }

    @Override
    public void update() {
        super.update();
    }
}
