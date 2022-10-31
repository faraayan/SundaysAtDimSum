package gameObjects.tracker;

import gameObjects.GameObject;

import java.awt.*;

// Organizes the player's level
public class Level extends GameObject {
    private int level;

    public int getLevel(){
        return level;
    }

    public int toNextLevel(){
        return level++;
    }

    public void setLevel(int level){
        this.level = level;
    }

    @Override
    public void drawImage(Graphics g) {
    }
}
