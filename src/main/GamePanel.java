package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTING
    public final int originalSize = 10;
    public final int scale = 3;

    public final int titleSize = originalSize * scale; // 48 * 48
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 20;
    public final int screenWidth = titleSize * maxScreenCol;
    public final int screenHeight = titleSize * maxScreenRow;

    Thread gameThread;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

//    // start threading
    @Override
    public void run() {


        while (gameThread != null){

            update();

            repaint();

        }

    }

    public void update(){}

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g.setColor(Color.PINK);
        g.fillRect(100, 100, titleSize, titleSize);
        g.dispose();

    }
}
