package main;

import tile.Tile;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTING
    public final int originalSize = 10;
    public final int scale = 3;

    public final int titleSize = originalSize * scale; // 48 * 48
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 20;
    public final int screenWidth = titleSize * maxScreenCol;
    public final int screenHeight = titleSize * maxScreenRow;

    // INJECTION
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // SET PLAYER
    int playerX = 100;
    int playerY = 100;
    int playerStep = titleSize;

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
        if (keyH.upPressed) {
            playerY -= playerStep;
        } else if (keyH.downPressed) {
            playerY += playerStep;
        } else if (keyH.leftPressed) {
            playerX -= playerStep;
        } else if (keyH.rightPressed) {
            playerX += playerStep;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // draw tile
        tileM.draw(g2);

        // character
        g.setColor(Color.PINK);
        g.fillRect(playerX, playerY, titleSize, titleSize);

        g.dispose();

    }
}
