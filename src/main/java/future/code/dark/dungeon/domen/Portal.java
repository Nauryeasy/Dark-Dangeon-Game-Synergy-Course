package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static future.code.dark.dungeon.config.Configuration.SPRITE_SIZE;

public class Portal extends AnimatedObject{
    boolean isUse = false;
    List<String> animatedListUse = Configuration.ANIMATED_PORTAL_USE;
    public Portal(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PORTAL_SPRITE, Configuration.ANIMATED_PORTAL);
    }

    @Override
    public void render(Graphics graphics) {
        if (!isUse) {
            graphics.drawImage(new ImageIcon(animatedList.get(countIdAnimated / 2)).getImage(), xPosition * SPRITE_SIZE, yPosition * SPRITE_SIZE, null);
            countIdAnimated++;
            if (countIdAnimated == 8) {
                countIdAnimated = 0;
            }
        } else {
            graphics.drawImage(new ImageIcon(animatedListUse.get(countIdAnimated / 4)).getImage(), xPosition * SPRITE_SIZE, yPosition * SPRITE_SIZE, null);
            countIdAnimated++;
            if (countIdAnimated == 16) {
                countIdAnimated = 0;
                isUse = false;
            }
        }
    }

    public void setIsUse() {
        isUse = true;
        countIdAnimated = 0;
    }
}
