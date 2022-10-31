package gameObjects.stationary;

import gameObjects.helper.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

// SlowSpeedTile: Special effect tile that slows ball speed when collided with
public class SlowSpeedTile extends Tile{

    public SlowSpeedTile(int x, int y, BufferedImage tileImage) {
        super(x, y, tileImage);
        biteSound = new Sound("resources/special.wav"); // has special sound effect
    }

    @Override
    public void tileTouched() {
        setState(0);
        biteSound.play();
    }
}
