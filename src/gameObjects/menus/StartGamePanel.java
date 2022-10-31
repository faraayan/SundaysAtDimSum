package gameObjects.menus;

import gameObjects.Launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StartGamePanel extends JPanel {

    private BufferedImage menuBackground;
    private JButton start;
    private JButton exit;
    private Launcher lf;

    public StartGamePanel(Launcher lf) {
        this.lf = lf;
        try {
            menuBackground = ImageIO.read(this.getClass().getClassLoader().getResource("title.png"));
        } catch (IOException e) {
            System.out.println("Error cant read menu background");
            e.printStackTrace();
            System.exit(-3);
        }
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        // Start button
        start = new JButton("Start");
        start.setFont(new Font("Avenir New", Font.BOLD ,24));
        start.setBackground(new Color(231, 215, 199));
        start.setOpaque(true);
        start.setBorderPainted(false);
        start.setBounds(470,552,244,75);
        start.addActionListener((actionEvent -> {
            this.lf.setFrame("game");
        }));

        // Exit button
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

        this.add(start);
        this.add(exit);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.menuBackground,0,0,null);
    }
}