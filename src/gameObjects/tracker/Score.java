package gameObjects.tracker;

import gameObjects.GameObject;

import java.awt.*;

// Tracks the score
public class Score extends GameObject {
    private int x, y;
    private int score;

    public int getScore(){
        return score;
    }

    public void increaseScoreBy(int score){
        this.score += score;
    }

    public void setScore(int score){
        this.score = score;
    }

    @Override
    public void drawImage(Graphics g) {
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
