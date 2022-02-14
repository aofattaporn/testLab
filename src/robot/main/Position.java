package robot.main;

import robot.player.Player;

import java.awt.*;


public class Position {

    GamePanel gp;
    Player player;

    int positionX;
    int positionY;

    public Position(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
    }

    public void update(int positionX, int positionY) {
        this.positionX = positionX / 30;
        this.positionY = positionY / 30;
    }


    public void draw(Graphics2D g2) {

        // SET BACKGROUND
        g2.setColor(Color.PINK);
        g2.fillRect(gp.titleSize * (gp.maxScreenCol - 5), 0, gp.titleSize * 5, gp.titleSize * 2);

        // FONT POSITION
        g2.setColor(Color.white);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Kanit", Font.PLAIN, 24);
        g2.setFont(font);

        // DRAW POSITION
        g2.drawString("x: " + (player.getWorldX() / 30 + 1) + " Y :" + (player.getWorldY() / 30 + 1),
                gp.titleSize * (gp.maxScreenCol - 4) - 15,
                40);


    }


}
