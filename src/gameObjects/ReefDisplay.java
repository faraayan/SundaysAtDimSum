package gameObjects;

import gameObjects.helper.Sound;
import gameObjects.stationary.*;
import gameObjects.helper.GameConstants;
import gameObjects.helper.MapLoader;
import gameObjects.helper.Resource;
import gameObjects.menus.EndGamePanel;
import gameObjects.moveable.Ball;
import gameObjects.moveable.Player;
import gameObjects.moveable.PlayerControl;
import gameObjects.tracker.Scoreboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static javax.imageio.ImageIO.read;


// Class that connects the environment of moveable and stationary objects of Tanks, their bullets, and walls
public class ReefDisplay extends JPanel implements Runnable {

    private BufferedImage world;
    public static Graphics2D buffer;
    private Player player;
    private Ball ball;
    private static boolean didWin = false;
    public static Scoreboard scoreboard;
    private Launcher lf;
    public static long tickCount = 0;
    public static ArrayList<Tile> tiles;
    JLabel ballSpeed;
    JLabel score;
    JLabel level;
    JLabel slowMini;
    JLabel healthMini;
    JLabel pointsMini;

    public ReefDisplay(Launcher lf){
        this.lf = lf;
    }

    @Override
    public void run(){
        try {
            this.resetGame();
            this.add(ballSpeed);
            this.add(slowMini);
            this.add(healthMini);
            this.add(pointsMini);
            this.add(score);
            this.add(level);
            while (true) {
                this.tickCount++;
                player.update();
                ball.move();
                searchForCollision(); // Detect collision
                this.repaint();
                Thread.sleep(1000 / 144);
                if(gameOver() || didWin){
                    this.lf.setFrame("end");
                    ballSpeed.setText("");
                    score.setText("");
                    level.setText("");
                    EndGamePanel.setEndScore(ReefDisplay.scoreboard.getScore());
                    EndGamePanel.setEndLevel(ReefDisplay.scoreboard.getLevel());
                    return;
                }
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    // Searches for whether there are collisions
    public void searchForCollision(){
        if(ball.getTouchedGround()){
            scoreboard.loseLife();
            if(!scoreboard.getIsDead()){
                resetRound();
                ball.setTouchedGround(false);
            }
        }
        checkForBallCollision(ball, player);
        for(int i = 0; i<tiles.size(); i++){ // Tests whether tank1 collides with walls
            if(checkForBallCollision(ball, tiles.get(i))){
                i = tiles.size();
            }
        }
    }

    public boolean checkForBallCollision(Ball ball, Collidable obj){
        if (!ball.getTouchedGround() && ball.getY() >= GameConstants.SCREEN_HEIGHT-3*ball.getImg().getHeight()) {
            ball.setY(GameConstants.SCREEN_HEIGHT-ball.getImg().getHeight()-Resource.getResourceImage("unbreak").getHeight());
            ball.setTouchedGround(true);
        }else if (ball.getRectangle().intersects(obj.getRectangle())) {
            if(obj instanceof Tile){
                ((Tile)obj).tileTouched();
                for(int i = 0; i<tiles.size(); i++){
                    if((tiles.get(i)).getState() <= 0){
                        tiles.remove(i);
                    }else{
                        tiles.get(i).drawImage(buffer);
                    }
                }
                ReefDisplay.scoreboard.increaseScore((Tile)obj);
                //Increase score
                if(obj instanceof HealthTile){
                    ReefDisplay.scoreboard.addLife();
                }else if(obj instanceof SlowSpeedTile){
                    ball.setSpeed(2);
                    //Decrease speed
                }else if(obj instanceof Boss){
                    if(!((Boss) obj).getTouched()){
                        ball.increaseBossCollisions();
                        if(ball.getBossCollisions() == Boss.getNumberOfBosses()){
                            nextLevel();
                        }
                    }
                    ((Boss) obj).setTouched(true);
                }
            }else if(obj instanceof Player){
                ((Player)obj).playBiteSound();
                ball.increaseSpeed();
            }
            ball.bounceBack(obj); // Make it case by case with different materials
            return true;
        }
        return false;
    }

    public void nextLevel(){
        if(scoreboard.getLevel() < 3){
            scoreboard.increaseLevel();
            try {
                loadMap("map" + scoreboard.getLevel());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resetRound();
        }else{
            didWin = true;
        }
    }

    // Returns whether the game has ended
    public boolean gameOver(){
        return scoreboard.getIsDead();
    }

    // Resets the tank game to its initial state
    public void resetRound(){
        Sound sound = new Sound("resources/slowSpeed.wav");
        ball.reset();
        player.reset();
        try {
            sound.play();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static boolean getDidWin(){
        return didWin;
    }

    // Resets the tank game to its initial state
    public void resetGame(){
        this.tickCount = 0;
        didWin = false;
        ball.setBossCollisions(0);
        Boss.setNumberOfBosses(0);
        init();
        scoreboard.fullyReset();
        resetRound();
    }

    // Loads map depending on map name
    public void loadMap(String mapName){
        MapLoader mapLoader = new MapLoader(); // Load maps
        mapLoader.setMapName(mapName);
        mapLoader.loadMap(tiles);
    }

    // Initializes the tank game
    public void init() {
        this.world = new BufferedImage(GameConstants.WORLD_WIDTH,
                GameConstants.SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        tiles = new ArrayList<>();
        loadMap("map1");

        player = new Player(600, -200, Resource.getResourceImage("player"));
        PlayerControl playerControl = new PlayerControl(player, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        this.lf.getJf().addKeyListener(playerControl);

        ball = new Ball(600, -100, 270, Resource.getResourceImage("ball"));

        scoreboard = new Scoreboard(GameConstants.SCREEN_WIDTH, 40, Resource.getResourceImage("life"));

        this.setLayout(null);
        ballSpeed = new JLabel("");
        slowMini = new JLabel("");
        healthMini = new JLabel("");
        pointsMini = new JLabel("");
        score = new JLabel("");
        level = new JLabel("");
    }

    // Draw entire game
    @Override
    public void paintComponent(Graphics g) {
        // Set Up
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D playerG = (Graphics2D) g;
        super.paintComponent(g2);
        super.paintComponent(playerG);
        buffer = world.createGraphics();
        buffer.setBackground(Color.WHITE);
        buffer.fillRect(0,0, GameConstants.WORLD_WIDTH, GameConstants.SCREEN_HEIGHT);
        BufferedImage bkgImg = null;
        try {
            bkgImg = read(Objects.requireNonNull(ReefDisplay.class.getClassLoader().getResource("Environment.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer.drawImage(bkgImg, 0, 0, null);

        // Draw the walls and tanks
        for(int i = 0; i<tiles.size(); i++){
            if((tiles.get(i)).getState() <= 0){
                tiles.remove(i);
            }else{
                tiles.get(i).drawImage(buffer);
            }
        }
        this.player.drawImage(buffer);
        this.ball.drawImage(buffer);

        // Draw the split screens
        BufferedImage screen = world.getSubimage(0, 0, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
        BufferedImage playerInfo = world.getSubimage(0, 0, GameConstants.WORLD_WIDTH-GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

        g2.drawImage(screen,0,0,null);
        g2.drawImage(playerInfo,GameConstants.SCREEN_WIDTH,0,null);

        // Draw the health menu for each player
        playerG.setColor(new Color(152, 227, 255));
        playerG.fillRect(GameConstants.SCREEN_WIDTH, 0, GameConstants.WORLD_WIDTH-GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
        updatePlayerInfo();
        scoreboard.drawImage(playerG, GameConstants.SCREEN_WIDTH,0);
    }

    public void updatePlayerInfo(){
        slowMini.setText(": slow speed");
        slowMini.setFont(new Font("Avenir New", Font.BOLD, 18));
        slowMini.setForeground(new Color(3, 147, 203));
        Dimension slowMiniSize = slowMini.getPreferredSize();
        slowMini.setIcon(new ImageIcon("resources/slowSpeedTileMini.png"));
        slowMini.setBounds(GameConstants.SCREEN_WIDTH + 30, 720, slowMiniSize.width, slowMiniSize.height);

        pointsMini.setText(": add 50 points");
        pointsMini.setFont(new Font("Avenir New", Font.BOLD, 18));
        pointsMini.setForeground(new Color(3, 147, 203));
        Dimension pointsMiniSize = pointsMini.getPreferredSize();
        pointsMini.setIcon(new ImageIcon("resources/extraPointTileMini.png"));
        pointsMini.setBounds(GameConstants.SCREEN_WIDTH + 30, 750, pointsMiniSize.width, pointsMiniSize.height);

        healthMini.setText(": extra life");
        healthMini.setFont(new Font("Avenir New", Font.BOLD, 18));
        healthMini.setForeground(new Color(3, 147, 203));
        Dimension healthMiniSize = healthMini.getPreferredSize();
        healthMini.setIcon(new ImageIcon("resources/HealthTileMini.png"));
        healthMini.setBounds(GameConstants.SCREEN_WIDTH + 30, 780, healthMiniSize.width, healthMiniSize.height);

        ballSpeed.setText("Ball Speed: "+((double)((int)(ball.getSpeed()*100)))/100);
        ballSpeed.setFont(new Font("Avenir New", Font.BOLD, 24));
        ballSpeed.setForeground(new Color(126, 195, 225));
        Dimension ballSpeedSize = ballSpeed.getPreferredSize();
        ballSpeed.setBounds(GameConstants.SCREEN_WIDTH + 30, 180, ballSpeedSize.width, ballSpeedSize.height);

        score.setText("Score: "+scoreboard.getScore());
        score.setFont(new Font("Avenir New", Font.BOLD, 24));
        score.setForeground(new Color(3, 147, 203));
        Dimension scoreSize = score.getPreferredSize();
        score.setBounds(GameConstants.SCREEN_WIDTH + 30, 130, scoreSize.width, scoreSize.height);

        level.setText("Meal: "+scoreboard.getLevel() + " of 3");
        level.setFont(new Font("Avenir New", Font.BOLD, 24));
        level.setForeground(new Color(3, 147, 203));
        Dimension levelSize = level.getPreferredSize();
        level.setBounds(GameConstants.SCREEN_WIDTH + 30, 80, levelSize.width, levelSize.height);
    }
}
