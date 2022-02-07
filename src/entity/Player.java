package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    // INJECTION
    GamePanel gp;
    KeyHandler keyH;

    // Constructor
    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();

    }

    // method set default
    public void setDefaultValues(){

        // SET PLAYER
        playerX = (gp.maxScreenCol / 2) * gp.titleSize;
        playerY = (gp.maxScreenRow / 2) * gp.titleSize;
        playerStep = 30;
        direction = "normal";

    }

    // method get images
    public void getPlayerImage(){
        try{
            normal = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("normal.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // method update (keybord)
    public void update(){
        if (keyH.upPressed) {
            direction = "normal";
            playerY -= playerStep;
        } else if (keyH.downPressed) {
            direction = "normal";
            playerY += playerStep;
        } else if (keyH.leftPressed) {
            direction = "normal";
            playerX -= playerStep;
        } else if (keyH.rightPressed) {
            direction = "normal";
            playerX += playerStep;
        }

    }

    // method draw
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        if ("normal".equals(direction)) {
            image = normal;
        }

        // draw image
        g2.drawImage(image, playerX, playerY, gp.titleSize, gp.titleSize, null);

    }

}
