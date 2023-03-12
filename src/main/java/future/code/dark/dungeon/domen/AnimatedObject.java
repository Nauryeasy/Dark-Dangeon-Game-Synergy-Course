package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static future.code.dark.dungeon.config.Configuration.SPRITE_SIZE;

public abstract class AnimatedObject extends GameObject{
    List<String> animatedList;
    int countIdAnimated = 0;
    public AnimatedObject(int xPosition, int yPosition, String imagePath, List<String> animatedList) {
        super(xPosition, yPosition, imagePath);
        this.animatedList = animatedList;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(new ImageIcon(animatedList.get(countIdAnimated / 4)).getImage(), xPosition * SPRITE_SIZE, yPosition * SPRITE_SIZE, null);
        countIdAnimated++;
        if (countIdAnimated == 16) {
            countIdAnimated = 0;
        }
    }

}
