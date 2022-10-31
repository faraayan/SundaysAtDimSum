package gameObjects.stationary;

import gameObjects.helper.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

// HealthTile: Special effect tile that gives player one extra life, max three lives
public class HealthTile extends Tile{
    public HealthTile(int x, int y, BufferedImage tileImage) {
        super(x, y, tileImage);
        biteSound = new Sound("resources/special.wav");
    }

    @Override
    public void tileTouched() {
        setState(0);
        biteSound.play();
    }

}
