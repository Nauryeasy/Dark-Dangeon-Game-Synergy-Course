package future.code.dark.dungeon.service;

import future.code.dark.dungeon.GameFrame;
import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.config.ImageView;
import future.code.dark.dungeon.domen.Coin;
import future.code.dark.dungeon.domen.DynamicObject;
import future.code.dark.dungeon.domen.Enemy;
import future.code.dark.dungeon.domen.Exit;
import future.code.dark.dungeon.domen.GameObject;
import future.code.dark.dungeon.domen.Map;
import future.code.dark.dungeon.domen.Player;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static future.code.dark.dungeon.config.Configuration.COIN_CHARACTER;
import static future.code.dark.dungeon.config.Configuration.ENEMIES_ACTIVE;
import static future.code.dark.dungeon.config.Configuration.ENEMY_CHARACTER;
import static future.code.dark.dungeon.config.Configuration.EXIT_CHARACTER;
import static future.code.dark.dungeon.config.Configuration.PLAYER_CHARACTER;

public class GameMaster {

    private static GameMaster instance;

    private final Map map;
    private final List<GameObject> gameObjects;

    static Integer countReceivedCoins = 0;

    private ImageView imageView;

    private Integer allCoins;
    private int countEnemyMove1 = 0;
    private int countEnemyMove2 = 0;
    private int speedEnemy1 = 10;
    private int speedEnemy2 = 25;
    private boolean flagSpeed = true;

    public static synchronized GameMaster getInstance() {
        if (instance == null) {
            instance = new GameMaster();
        }
        return instance;
    }

    private GameMaster() {
        try {
            this.map = new Map(Configuration.MAP_FILE_PATH);
            this.gameObjects = initGameObjects(map.getMap());
            this.allCoins = getCoinsObjects().size();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<GameObject> initGameObjects(char[][] map) {
        List<GameObject> gameObjects = new ArrayList<>();
        Consumer<GameObject> addGameObject = gameObjects::add;
        Consumer<Enemy> addEnemy = enemy -> {if (ENEMIES_ACTIVE) gameObjects.add(enemy);};

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case EXIT_CHARACTER -> addGameObject.accept(new Exit(j, i));
                    case COIN_CHARACTER -> addGameObject.accept(new Coin(j, i));
                    case ENEMY_CHARACTER -> addEnemy.accept(new Enemy(j, i));
                    case PLAYER_CHARACTER -> addGameObject.accept(new Player(j, i));
                }
            }
        }

        return gameObjects;
    }

    public void renderFrame(Graphics graphics) {
        getMap().render(graphics);

        if (getExitObject().get(0).getXPosition() == getPlayer().getXPosition() & getExitObject().get(0).getYPosition() == getPlayer().getYPosition()
                & getCoinsObjects().size() == 0) {
            graphics.drawImage(new ImageIcon(Configuration.IMAGE_WIN).getImage(), 200, 0, imageView);
            getPlayer().stepSize = 0;
        } else if((getEnemies().get(0).getXPosition() == getPlayer().getXPosition() & getEnemies().get(0).getYPosition() == getPlayer().getYPosition()) | (getEnemies().get(1).getXPosition() == getPlayer().getXPosition() & getEnemies().get(1).getYPosition() == getPlayer().getYPosition())) {
                graphics.drawImage(new ImageIcon(Configuration.IMAGE_LOSE).getImage(), 285, 170, imageView);
                getPlayer().stepSize = 0;
                getEnemies().get(0).stepSize = 0;
                getEnemies().get(1).stepSize = 0;

        } else {
            getStaticObjects().forEach(gameObject -> gameObject.render(graphics));

            for(GameObject ob: getCoinsObjects()) {
                if (ob.getXPosition() == getPlayer().getXPosition() & ob.getYPosition() == getPlayer().getYPosition()) {
                    gameObjects.remove(ob);
                    countReceivedCoins ++;
                }
            }

            for(Enemy ob: getEnemies()) {
                int lastX = ob.getXPosition();
                int lastY = ob.getYPosition();
                if (countEnemyMove1 == speedEnemy1 & getEnemies().get(0) == ob) {
                    ob.move(getPlayer().getXPosition(), getPlayer().getYPosition());
                    countEnemyMove1 = 0;
                }

                if (countEnemyMove2 == speedEnemy2 & getEnemies().get(1) == ob) {
                    ob.move(getPlayer().getXPosition(), getPlayer().getYPosition());
                    countEnemyMove2 = 0;
                }
            }

            if (allCoins - countReceivedCoins == 2 & flagSpeed) {
                speedEnemy1 = 5;
                speedEnemy2 = 5;
                countEnemyMove1 = 0;
                countEnemyMove2 = 0;
                flagSpeed = false;
            }

            getEnemies().forEach(gameObject -> gameObject.render(graphics));
            getPlayer().render(graphics);
            graphics.setColor(Color.WHITE);
            graphics.drawString(getPlayer().toString(), 10, 20);
            graphics.drawString("Coins collected: " + countReceivedCoins, 10, 40);
            graphics.drawString("Coins left: " + (allCoins - countReceivedCoins), 10, 60);
            countEnemyMove1++;
            countEnemyMove2++;
        }
    }

    public Player getPlayer() {
        return (Player) gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Player)
                .findFirst()
                .orElseThrow();
    }

    private List<GameObject> getStaticObjects() {
        return gameObjects.stream()
                .filter(gameObject -> !(gameObject instanceof DynamicObject))
                .collect(Collectors.toList());
    }

    private List<GameObject> getExitObject() {
        return gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Exit)
                .collect(Collectors.toList());
    }

    public List<GameObject> getCoinsObjects() {
        return gameObjects.stream()
                .filter(gameObject -> !(gameObject instanceof DynamicObject) & gameObject instanceof Coin)
                .collect(Collectors.toList());
    }

    private List<Enemy> getEnemies() {
        return gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Enemy)
                .map(gameObject -> (Enemy) gameObject)
                .collect(Collectors.toList());
    }

    public Map getMap() {
        return map;
    }

}
