package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int playerStep;

    public BufferedImage down, left, right, up;
    public String direction;
    public String directionBF;

    public Rectangle soidArea;
    public boolean collisionOn = false;




}

