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

    // DECLARE VARIABLE
    public final int screenX;
    public final int screenY;



    // Constructor
    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = ((gp.maxScreenCol - 1) / 2) *  gp.titleSize ;
        screenY = (gp.screenHeight) / 2;

        setDefaultValues();
        getPlayerImage();

    }

    // method set default
    public void setDefaultValues(){

        // SET PLAYER
        worldX = gp.titleSize * 50;
        worldY = gp.titleSize * 40;

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
            worldY -= playerStep;
        } else if (keyH.downPressed) {
            direction = "normal";
            worldY += playerStep;
        } else if (keyH.leftPressed) {
            direction = "normal";
            worldX -= playerStep;
        } else if (keyH.rightPressed) {
            direction = "normal";
            worldX += playerStep;
        }

    }

    // method draw
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        if ("normal".equals(direction)) {
            image = normal;
        }

        // draw image
        g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);

    }

}
