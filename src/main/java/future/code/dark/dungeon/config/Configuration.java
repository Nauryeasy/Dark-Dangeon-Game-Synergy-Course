package future.code.dark.dungeon.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Configuration {

    String GAME_NAME="Dark Dungeon";
    String MAP_FILE_PATH = "src/main/resources/maps/map.ber";
    Boolean ENEMIES_ACTIVE = true;
    int MILLISECOND_PER_FRAME = 40;
    char WALL_CHARACTER = '1';
    char EXIT_CHARACTER = 'E';
    char LAND_CHARACTER = '0';
    char PLAYER_CHARACTER = 'P';
    char ENEMY_CHARACTER = 'G';
    char COIN_CHARACTER = 'C';
    char PORTAL_CHARACTER = 'O';
    Integer SPRITE_SIZE = 64;
    String PLAYER_SPRITE = "src/main/resources/assets/hero/tile000.png";
    String GHOST_SPRITE = "src/main/resources/assets/ghost/tile000.png";
    String WALL_SPRITE = "src/main/resources/assets/land/wall.png";
    String LAND_SPRITE = "src/main/resources/assets/land/ground.png";
    String PORTAL_SPRITE = "src/main/resources/assets/land/portal.png";
    String EXIT_SPRITE = "src/main/resources/assets/land/out.png";
    String COIN_SPRITE = "src/main/resources/assets/land/collectible.png";
    String IMAGE_WIN = "src/main/resources/assets/victory.jpg";
    String IMAGE_LOSE = "src/main/resources/assets/game_over_screen.jpeg";
    List<String> ANIMATED_PLAYER = Arrays.asList("src/main/resources/assets/hero/tile000.png", "src/main/resources/assets/hero/tile001.png", "src/main/resources/assets/hero/tile002.png", "src/main/resources/assets/hero/tile003.png");
    List<String> ANIMATED_ENEMY = Arrays.asList("src/main/resources/assets/ghost/tile000.png", "src/main/resources/assets/ghost/tile001.png", "src/main/resources/assets/ghost/tile002.png", "src/main/resources/assets/ghost/tile003.png");

}
