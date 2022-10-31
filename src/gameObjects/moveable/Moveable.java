package gameObjects.moveable;


import gameObjects.GameObject;
import gameObjects.helper.GameConstants;
import gameObjects.helper.Resource;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Moveable extends GameObject {
    int x;
    int y;
    int angle;
    Rectangle hitBox;
    protected BufferedImage img;
    private boolean hasCollided = false;

    public Moveable(int x, int y, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.img = img;
        this.hitBox = new Rectangle(x,y,this.img.getWidth(), this.img.getHeight());
    }

    // Checks whether a moveable object will be out of bounds
    public boolean checkBorder() {
        if (x < 0+Resource.getResourceImage("unbreak").getWidth()) {
            x = 0+Resource.getResourceImage("unbreak").getWidth();
            return true;
        }
        if (x >= GameConstants.SCREEN_WIDTH-img.getWidth()-Resource.getResourceImage("unbreak").getWidth()) {
            x = GameConstants.SCREEN_WIDTH-img.getWidth()-Resource.getResourceImage("unbreak").getWidth();
            return true;
        }
        return false;
    }

    // Draws the image of the object
    @Override
    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRectangle() {
        return hitBox.getBounds();
    }

    public boolean getHasCollided() {
        return hasCollided;
    }

}
