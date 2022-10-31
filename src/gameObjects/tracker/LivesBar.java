package gameObjects.tracker;

import gameObjects.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

// Tracks the amount of lives a player has
public class LivesBar extends GameObject {

    private int x, y;
    private int playerLives = 3; // Each player is given 3 lives
    BufferedImage lifeImage;

    public LivesBar(int x, int y, BufferedImage lifeImage) {
        this.x = x;
        this.y = y;
        this.lifeImage = lifeImage;
    }

    public void addLife(){
        if(playerLives<3){
            playerLives++;
        }
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i<playerLives; i++){
            AffineTransform rotation = AffineTransform.getTranslateInstance(x + i*50+20, y+20);
            g2d.drawImage(this.lifeImage, rotation, null);
        }
    }

    public void loseLife(){
        playerLives--;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLives(int lives){
        playerLives = lives;
    }

    public int getLives(){
        return playerLives;
    }
}
