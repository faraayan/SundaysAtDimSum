package gameObjects.stationary;

import gameObjects.ReefDisplay;
import gameObjects.helper.Resource;
import gameObjects.helper.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

// RegularTile: tile that can have range of 1 to 5 collisions before disappearing, increases score by 10
public class RegularTile extends Tile{
    BufferedImage tileImage;
    public RegularTile(int x, int y, int state, BufferedImage tileImage) {
        super(x, y, tileImage);
        super.setState(state);
        biteSound = new Sound("resources/pop.wav");
    }

    @Override
    public void tileTouched(){
        biteSound.play();
        this.state--;
        drawImage(ReefDisplay.buffer);
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        switch(getState()){
            case 5:
                tileImage = Resource.getResourceImage("tile5");
                break;
            case 4:
                tileImage = Resource.getResourceImage("tile4");
                break;
            case 3:
                tileImage = Resource.getResourceImage("tile3");
                break;
            case 2:
                tileImage = Resource.getResourceImage("tile2");
                break;
            case 1:
                tileImage = Resource.getResourceImage("tile1");
                break;
            default:
                break;
        }
        g2.drawImage(this.tileImage,x,y,null);
    }
}
