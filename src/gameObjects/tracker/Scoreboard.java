package gameObjects.tracker;

import gameObjects.GameObject;
import gameObjects.stationary.ExtraPointTile;
import gameObjects.stationary.Tile;
import gameObjects.stationary.UnbreakTile;

import java.awt.*;
import java.awt.image.BufferedImage;

// Tracks score of player
public class Scoreboard extends GameObject {
    int x, y;
    private LivesBar livesBar;
    private Score score;
    private Level level;
    private boolean isDead = false;

    public Scoreboard(int x, int y, BufferedImage lifeimg) {
        livesBar = new LivesBar(x, y, lifeimg);
        score = new Score();
        level = new Level();
    }

    // Draws lives bar and score
    public void drawImage(Graphics g, int x, int y) {
        livesBar.setX(x);
        livesBar.setY(y);
        score.setX(x);
        score.setY(y+100);
        livesBar.drawImage(g);
        score.drawImage(g);
    }

    public int getLevel(){
        return level.getLevel();
    }

    public void increaseLevel(){
        level.toNextLevel();
    }

    // Increases score depending on which block it is
    public void increaseScore(Tile tile){
        if(!(tile instanceof UnbreakTile)){
            if(tile instanceof ExtraPointTile){
                score.increaseScoreBy(50);
            }else{
                score.increaseScoreBy(10);
            }
        }
    }

    public void reset() {
        livesBar.setLives(3);
        isDead = false;
        level.setLevel(1);
    }

    public void addLife(){
        livesBar.addLife();
    }

    public void fullyReset(){
        reset();
        score.setScore(0);
    }

    public boolean getIsDead(){
        return isDead;
    }

    public int getScore(){
        return score.getScore();
    }

    public void loseLife(){
        livesBar.loseLife();
        if(livesBar.getLives() == 0){
            isDead = true;
        }
    }

    @Override
    public void drawImage(Graphics g) {
    }
}
