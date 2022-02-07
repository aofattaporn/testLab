package main;

//import entity.Player;
import entity.Player;
import tile.Tile;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTING
    public final int originalSize = 10;
    public final int scale = 3;

    public final int titleSize = originalSize * scale; // 30 * 30
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 20;

    public final int screenWidth = titleSize * maxScreenCol; // 750
    public final int screenHeight = titleSize * maxScreenRow; //300

    // WORLD SETTING
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 80;
    public final int worldWidth = titleSize * maxWorldCol; // 3000
    public final int worldHeight = titleSize * maxWorldRow; // 2400


    // INJECTION
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);


    // FPS
    int FPS = 18;

    // constructor
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;

            }

            if (timer >= 1){
//                System.out.println("FPS : " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        // draw tile
        tileM.draw(g2);

        // character
        player.draw(g2);


        g.dispose();

    }
}
