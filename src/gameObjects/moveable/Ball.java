package gameObjects.moveable;

import gameObjects.Collidable;
import gameObjects.ReefDisplay;
import gameObjects.helper.Sound;
import gameObjects.stationary.*;
import gameObjects.helper.GameConstants;
import gameObjects.helper.Resource;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Ball extends Moveable implements Collidable {
     // Speed of bullet
    boolean touchedGround = false;
    int bossCollisions = 0;
    double speed = 3;
    int vx;
    int vy;

    public Ball(int x, int y, int angle, BufferedImage img) {
        super(x, y, angle, img);
    }

    public void move(){
        if(!this.getHasCollided()){
            vx = (int) Math.round(speed * Math.cos(Math.toRadians(angle)));
            vy = (int) Math.round(speed * Math.sin(Math.toRadians(angle)));
            x += vx;
            y += vy;
            this.hitBox.setLocation(x,y);
        }
    }

    @Override
    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
    }

    public void increaseBossCollisions(){
        this.bossCollisions++;
    }

    public int getBossCollisions() {
        return bossCollisions;
    }

    public void setBossCollisions(int bossCollisions) {
        this.bossCollisions = bossCollisions;
    }

    public boolean getTouchedGround(){
        return touchedGround;
    }

    public void setTouchedGround(boolean touchedGround){
        this.touchedGround = touchedGround;
    }

    public void setAngle(int angle){
        this.angle = angle;
    }

    public void reset(){
        x = 600;
        y = 600;
        angle = 270 + (int)(Math.random()*45)-22;
        speed = 3;
        touchedGround = false;
        if(vy>0){
            System.out.println("This ran!");
            vy *=-1;
        }
    }

    // Bounces back from an object depending on the type of object
    public void bounceBack(Collidable obj){
        if(obj instanceof UnbreakTile && vy>0 && y<GameConstants.SCREEN_HEIGHT-2*Resource.getResourceImage("unbreak").getHeight()){
            if(x<100){ // If ball is on left edge
                angle = 45 + (int)(Math.random() * 20);
            }else{ // If ball is on right edge
                angle = 135 + (int)(Math.random() * 20);
            }
        }else if(obj instanceof Player) {
            if(this.getRectangle().intersects(obj.getRectangle())) {
                y-=10;
            }
            if(x+img.getWidth()/2 > ((Player)obj).getX()+((Player)obj).getWidth()/2){ // If ball is on right of player
                angle = 280 + (int)(Math.random() * 45);
            }else{ // If ball is on left of player
                angle = 260 - (int)(Math.random() * 45);
            }
        }else{
            angle += 180 + (int)(Math.random() * 45) - 22;
        }
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public BufferedImage getImg(){
        return img;
    }

    public void increaseSpeed(){
        if(speed<4){
            speed += 0.1;
        }
    }

    public double getSpeed(){
        return speed;
    }

    public void setX(int x){ super.x = x; }

    public void setY(int y) { this. y = y;}

}
