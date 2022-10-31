package gameObjects.menus;

import gameObjects.Launcher;
import gameObjects.ReefDisplay;
import gameObjects.helper.GameConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EndGamePanel extends JPanel {

    private BufferedImage menuBackground;
    private JButton start;
    private JButton exit;
    private Launcher lf;
    public static int endScore;
    public static int endLevel;
    JLabel winOrLose;
    JLabel score;
    JLabel level;

    public static void setEndLevel(int end) {
        endLevel = end;
    }

    public static void setEndScore(int end) {
        endScore = end;
    }

    public EndGamePanel(Launcher lf) {
        this.lf = lf;
        try {
            menuBackground = ImageIO.read(this.getClass().getClassLoader().getResource("end.png"));
        } catch (IOException e) {
            System.out.println("Error cant read menu background");
            e.printStackTrace();
            System.exit(-3);
        }
        this.setBackground(Color.pink);
        this.setLayout(null);

        start = new JButton("Play Again");
        start.setFont(new Font("Avenir New", Font.BOLD ,24));
        start.setBackground(new Color(231, 215, 199));
        start.setOpaque(true);
        start.setBorderPainted(false);
        start.setBounds(470,552,244,75);
        start.addActionListener((actionEvent -> {
            this.lf.setFrame("game");
        }));

        exit = new JButton("Exit");
        exit.setSize(new Dimension(200,100));
        exit.setBackground(new Color(215, 195, 172));
        exit.setOpaque(true);
        exit.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        exit.setBorderPainted(false);
        exit.setFont(new Font("Avenir New", Font.BOLD ,24));
        exit.setBounds(490,671,203,75);
        exit.addActionListener((actionEvent -> {
            this.lf.closeGame();
        }));
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.menuBackground,0,0,null);

        // Win or lose label
        winOrLose = new JLabel("");
        if(ReefDisplay.getDidWin()){
            winOrLose = new JLabel("Congrats, you won!");
        }else{
            winOrLose.setText("Good try. You almost had it!");
        }

        winOrLose.setFont(new Font("Avenir New", Font.BOLD ,40));
        winOrLose.setForeground(new Color(248, 253, 255));
        Dimension winOrLoseSize = winOrLose.getPreferredSize();
        winOrLose.setBounds(GameConstants.END_MENU_SCREEN_WIDTH/2-winOrLoseSize.width/2, 250, winOrLoseSize.width, winOrLoseSize.height);

        score = new JLabel("Your Score: " + endScore);
        score.setFont(new Font("Avenir New", Font.BOLD ,30));
        score.setForeground(new Color(228, 248, 255));
        Dimension scoreSize = score.getPreferredSize();
        score.setBounds(GameConstants.END_MENU_SCREEN_WIDTH/2-scoreSize.width/2-160, 400, scoreSize.width, scoreSize.height);

        level = new JLabel("Level Reached: " + endLevel);
        level.setFont(new Font("Avenir New", Font.BOLD ,30));
        level.setForeground(new Color(228, 248, 255));
        Dimension levelSize = level.getPreferredSize();
        level.setBounds(GameConstants.END_MENU_SCREEN_WIDTH/2-scoreSize.width/2+130, 400, levelSize.width, levelSize.height);

        this.add(start);
        this.add(winOrLose);
        this.add(exit);
        this.add(score);
        this.add(level);
    }
}