package robot.main;

//import entity.Player;

import robot.player.Player;
import robot.tile.TileManager;

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

    public boolean state = true;

    // WORLD SETTING
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 80;
    public final int worldWidth = titleSize * maxWorldCol; // 3000
    public final int worldHeight = titleSize * maxWorldRow; // 2400

    // INJECTION
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH, tileM);
    public Thread gameThread;
    public Position position = new Position(this, player);


    // FPS
    int FPS = 18;

    // constructor
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

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
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update() {

        player.update();

        closeWindow(state);

        position.update(player.getWorldX(), player.getWorldY());

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // draw robot.tile
        tileM.draw(g2);

        // character
        player.draw(g2);

        // draw position
        position.draw(g2);


        g.dispose();

    }

    public void closeWindow(boolean state){
        if (!state){

            JFrame window2 = new JFrame();
            window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window2.setResizable(false);
            window2.setTitle("GAME ROBOT2");

            System.exit(1);
            gameThread.stop();

        }
    }

}
