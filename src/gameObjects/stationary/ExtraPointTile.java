package gameObjects.stationary;

import gameObjects.helper.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

// ExtraPointTile: Special effect tile that gives player 50 points when collided with
public class ExtraPointTile extends Tile{
    public ExtraPointTile(int x, int y, BufferedImage tileImage) {
        super(x, y, tileImage);
        biteSound = new Sound("resources/special.wav");
    }

    @Override
    public void tileTouched() {
        setState(0);
        biteSound.play();
    }
}
