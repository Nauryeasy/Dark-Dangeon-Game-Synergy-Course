package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

public class Enemy extends DynamicObject{

    public int stepSize = 1;

    public Enemy(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.GHOST_SPRITE, Configuration.ANIMATED_ENEMY);
    }

    public void move(int playerX, int playerY) {
        Direction direction = null;
        int randomDirection = -1;
        if (xPosition < playerX & yPosition < playerY) {
            if (GameMaster.getInstance().getMap().getMap()[yPosition + 1][xPosition] == Configuration.WALL_CHARACTER & GameMaster.getInstance().getMap().getMap()[yPosition][xPosition + 1] == Configuration.WALL_CHARACTER) {
                randomDirection = (int)(Math.random() * 2);
            } else if(GameMaster.getInstance().getMap().getMap()[yPosition + 1][xPosition] == Configuration.WALL_CHARACTER){
                randomDirection = 3;
            } else if(GameMaster.getInstance().getMap().getMap()[yPosition][xPosition + 1] == Configuration.WALL_CHARACTER) {
                randomDirection = 2;
            } else randomDirection = (int)(Math.random() * 2 + 2);
        } else if (xPosition > playerX & yPosition > playerY) {
            if (GameMaster.getInstance().getMap().getMap()[yPosition - 1][xPosition] == Configuration.WALL_CHARACTER & GameMaster.getInstance().getMap().getMap()[yPosition][xPosition - 1] == Configuration.WALL_CHARACTER) {
                randomDirection = (int)(Math.random() * 2 + 2);
            } if (GameMaster.getInstance().getMap().getMap()[yPosition - 1][xPosition] == Configuration.WALL_CHARACTER) {
                randomDirection = 1;
            } else if (GameMaster.getInstance().getMap().getMap()[yPosition][xPosition - 1] == Configuration.WALL_CHARACTER) {
                randomDirection = 0;
            } else randomDirection = (int)(Math.random() * 2);
        } else if (xPosition > playerX & yPosition < playerY) {
            if (GameMaster.getInstance().getMap().getMap()[yPosition + 1][xPosition] == Configuration.WALL_CHARACTER) {
                randomDirection = 1;
            } else if (GameMaster.getInstance().getMap().getMap()[yPosition][xPosition - 1] == Configuration.WALL_CHARACTER) {
                randomDirection = 2;
            } else randomDirection = (int)(Math.random() * 2 + 1);
        } else if (xPosition < playerX & yPosition > playerY) {
            if (GameMaster.getInstance().getMap().getMap()[yPosition - 1][xPosition] == Configuration.WALL_CHARACTER) {
                randomDirection = 1;
            } else if (GameMaster.getInstance().getMap().getMap()[yPosition][xPosition + 1] == Configuration.WALL_CHARACTER) {
                randomDirection = 0;
            } else randomDirection = (int)(Math.random() * 2);
            if (randomDirection == 0) {
            } else randomDirection = 3;
        } else if (xPosition == playerX & yPosition <= playerY) {
            if (GameMaster.getInstance().getMap().getMap()[yPosition + 1][xPosition] == Configuration.WALL_CHARACTER) {
                if (xPosition <= (17 / 2)) {
                    randomDirection = 3;
                } else randomDirection = 1;
            } else randomDirection = 2;
        } else if (xPosition == playerX & yPosition > playerY) {
            if (GameMaster.getInstance().getMap().getMap()[yPosition - 1][xPosition] == Configuration.WALL_CHARACTER) {
                if (xPosition <= (17 / 2)) {
                    randomDirection = 3;
                } else randomDirection = 1;
            } else randomDirection = 0;
        } else if (xPosition < playerX & yPosition == playerY) {
            if (GameMaster.getInstance().getMap().getMap()[yPosition][xPosition + 1] == Configuration.WALL_CHARACTER) {
                if (xPosition <= (11 / 2)) {
                    randomDirection = 2;
                } else randomDirection = 0;
            } else randomDirection = 3;
        } else if (xPosition > playerX & yPosition == playerY) {
            if (GameMaster.getInstance().getMap().getMap()[yPosition][xPosition - 1] == Configuration.WALL_CHARACTER) {
                if (xPosition <= (11 / 2)) {
                    randomDirection = 2;
                } else randomDirection = 0;
            } else randomDirection = 1;
        } else randomDirection = (int)(Math.random() * 4);

        switch (randomDirection) {
            case 0 -> direction = Direction.UP;
            case 1 -> direction = Direction.LEFT;
            case 2 -> direction = Direction.DOWN;
            case 3 -> direction = Direction.RIGHT;
        }
        super.move(direction, stepSize);
    }

}
