package gameObjects.stationary;

import gameObjects.Collidable;
import gameObjects.GameObject;
import gameObjects.ReefDisplay;
import gameObjects.helper.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile extends GameObject implements Collidable {
    int x, y;
    int state = 1;
    Rectangle hitBox;
    BufferedImage tileImage;
    private boolean hasCollided = false;
    Sound biteSound;

    public void playBiteSound(){
        biteSound.play();
    }

    public Tile(int x, int y, BufferedImage tileImage){
        this.x = x;
        this.y = y;
        this.tileImage = tileImage;
        this.hitBox = new Rectangle(x,y,this.tileImage.getWidth(), this.tileImage.getHeight());
        this.hitBox.setLocation(x,y);
    }

    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state = state;
    }

    public int getX() {
        return hitBox.x;
    }

    public int getY() {
        return hitBox.y;
    }

    public Rectangle getRectangle() {
        return hitBox.getBounds();
    }

    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(this.tileImage,x,y,null);
    }

    public abstract void tileTouched();

}