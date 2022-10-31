package gameObjects.stationary;

import gameObjects.Collidable;
import gameObjects.GameObject;
import gameObjects.helper.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

// Boss Tile: Family members, must collide with all bosses to pass level
public class Boss extends Tile{
    public static int numberOfBosses = 0;
    private boolean touched = false;

    public Boss(int x, int y, BufferedImage tileImage) {
        super(x, y, tileImage);
        numberOfBosses++;
        biteSound = new Sound("resources/bite.wav"); // biting and eating sound
    }

    public static void setNumberOfBosses(int numberOfBosses) {
        Boss.numberOfBosses = numberOfBosses;
    }

    public static int getNumberOfBosses() {
        return numberOfBosses;
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(this.tileImage,x,y,null);
    }

    public boolean getTouched(){
        return touched;
    }

    public void setTouched(boolean touched){
        this.touched = touched;
    }

    @Override
    public void tileTouched() {
        biteSound.play();
        setState(0);
    }
}
