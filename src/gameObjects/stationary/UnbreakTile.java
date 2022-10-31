package gameObjects.stationary;

import gameObjects.helper.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

// UnbreakTile: tile that does not break, no matter how many collisions
public class UnbreakTile extends Tile{
    public UnbreakTile(int x, int y, BufferedImage tileImage) {
        super(x, y, tileImage);
        biteSound = new Sound("resources/pop.wav");
    }

    @Override
    public void tileTouched() {
        biteSound.play();
    }
}
