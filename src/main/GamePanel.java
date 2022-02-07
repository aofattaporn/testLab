package main;

//import entity.Player;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    // Variable
    public final int[] randCol;
    public final int[] randRow;

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
    public Position position = new Position();

    // INJECTION
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    public Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Bomb bomb = new Bomb(tileM, this);


    // FPS
    int FPS = 18;

    // constructor
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        randCol = randomBomb();
        randRow = randomBomb();


    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;

            }

            if (timer >= 1) {
//                System.out.println("FPS : " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update() {

        player.update();
        position.update(player.getWorldX(), player.getWorldY());

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // draw tile
        tileM.draw(g2);

        // character
        player.draw(g2);

//        position.draw(g2);
        g.setColor(Color.PINK);
        g.fillRect(titleSize * (maxScreenCol - 5), 0, titleSize * 5, titleSize * 2);


        g.dispose();

    }

    public int[] randomBomb() {

        Random rand = new Random();

        int[] randBlock = new int[80];
        int i = 0;

        while (i < 80) {

            int number = rand.nextInt(80) + 1;
            if (number % 30 == 0) {
                randBlock[i] = number;
                i++;
                System.out.println(i +" :" + randBlock.length);

            }
        }

        return randBlock;
    }


}
