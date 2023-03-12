package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

public class Player extends DynamicObject {
    public int stepSize = 1;

    public Player(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PLAYER_SPRITE, Configuration.ANIMATED_PLAYER);
    }

    public void move(Direction direction) {
        super.move(direction, stepSize);
    }

    @Override
    public String toString() {
        return "Player{[" + xPosition + ":" + yPosition + "]}";
    }
}
