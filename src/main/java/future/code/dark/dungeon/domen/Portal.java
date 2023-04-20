package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

public class Portal extends GameObject{
    public Portal(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PORTAL_SPRITE);
    }
}
