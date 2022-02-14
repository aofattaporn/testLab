package robot.player;

import robot.main.GamePanel;
import robot.main.KeyHandler;
import robot.tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    // DECLARE VARIABLE
    public final int screenX;
    public final int screenY;

    // INJECTION
    GamePanel gp;
    KeyHandler keyH;
    TileManager tileM;

    // Constructor
    public Player(GamePanel gp, KeyHandler keyH, TileManager tileM) {

        this.tileM = tileM;
        this.gp = gp;
        this.keyH = keyH;

        screenX = ((gp.maxScreenCol - 1) / 2) * gp.titleSize;
        screenY = (gp.screenHeight) / 2;

        setDefaultValues();
        getPlayerImage();

    }

    // method set default
    public void setDefaultValues() {

        // SET PLAYER
        worldX = gp.titleSize * 50;
        worldY = gp.titleSize * 40;

        playerStep = 30;
        direction = "down";
        directionBF = "down";

    }

    // method get images
    public void getPlayerImage() {
        try {
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("down.png")));
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("up.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("left.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("righ.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method update (keybord)
    public void update() {
        if (keyH.upPressed) {
            if (worldY >= 30) {
                if (directionBF.equals("up")) worldY -= playerStep;
                else {
                    directionBF = "up";
                    direction = "up";
                }

            }
        } else if (keyH.downPressed) {

            if (worldY <= gp.titleSize * (gp.maxWorldRow - 2)) {
                if (directionBF.equals("down")) worldY += playerStep;
                else {
                    directionBF = "down";
                    direction = "down";
                }

            }
        } else if (keyH.leftPressed) {
            if (worldX >= gp.titleSize) {
                if (directionBF.equals("left")) worldX -= playerStep;
                else {
                    directionBF = "left";
                    direction = "left";
                }
            }

        } else if (keyH.rightPressed) {
            direction = "right";
            if (worldX <= gp.titleSize * (gp.maxWorldCol - 2)) {
                if (directionBF.equals("right")) worldX += playerStep;
                else {
                    directionBF = "right";
                    direction = "right";
                }
            }
        }

    }

    // method draw
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "down":
                image = down;
                break;
            case "up":
                image = up;
                break;
            case "left":
                image = left;
                break;
            case "right":
                image = right;
                break;
        }

        // check bomb
        int i = 0;

        if (worldX / 30 == 48 && worldY / 30 == 35 || worldX / 30 == 60 && worldY / 30 == 33) {
            gp.state = false;

        } else {
            g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);

        }

    }

    ;


    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

}
