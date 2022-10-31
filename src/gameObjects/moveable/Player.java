package gameObjects.moveable;

import gameObjects.Collidable;
import gameObjects.helper.Sound;

import java.awt.image.BufferedImage;

public class Player extends Moveable implements Collidable {
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean moveRight = true;
    private boolean moveLeft = true;
    Sound biteSound = new Sound("resources/pop.wav");

    public Player(int x, int y, BufferedImage img) {
        super(x, y, 0, img);
    }

    public void update() {
        if (this.LeftPressed) {
            this.moveLeft();
        }
        if (this.RightPressed) {
            this.moveRight();
        }
    }

    public int getWidth(){
        return img.getWidth();
    }

    // Used for restarting the game
    public void reset(){
        x = 550;
        y = 650;
        LeftPressed = false;
        RightPressed = false;
        moveLeft = true;
        moveRight = true;
        checkBorder();
        this.hitBox.setLocation(x,y);
    }

    // To move backwards
    private void moveLeft() {
        x-=4;
        checkBorder();
        this.hitBox.setLocation(x,y);
    }

    public void setX(int x){ super.x = x; }

    public void setY(int y) { this. y = y;}


    // To move forwards
    private void moveRight() {
        x+=4;
        checkBorder();
        this.hitBox.setLocation(x,y);
    }

    public void playBiteSound(){
        biteSound.play();
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }


}
