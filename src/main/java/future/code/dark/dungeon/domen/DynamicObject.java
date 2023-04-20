package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

import java.awt.*;
import java.util.List;

public abstract class DynamicObject extends AnimatedObject {

    public boolean checkTP = true;

    public DynamicObject(int xPosition, int yPosition, String imagePath, List<String> animatedList) {
        super(xPosition, yPosition, imagePath, animatedList);
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    protected void move(Direction direction, int distance) {
        int tmpXPosition = getXPosition();
        int tmpYPosition = getYPosition();

        switch (direction) {
            case UP -> tmpYPosition -= distance;
            case DOWN -> tmpYPosition += distance;
            case LEFT -> tmpXPosition -= distance;
            case RIGHT -> tmpXPosition += distance;
        }

        if (isAllowedSurface(tmpXPosition, tmpYPosition)) {
            xPosition = tmpXPosition;
            yPosition = tmpYPosition;
        }

        this.checkTP = true;
    }

    private Boolean isAllowedSurface(int x, int y) {
        return GameMaster.getInstance().getMap().getMap()[y][x] != Configuration.WALL_CHARACTER & !(GameMaster.getInstance().getMap().getMap()[y][x] == Configuration.EXIT_CHARACTER & GameMaster.getInstance().getCoinsObjects().size() != 0);
    }

}
