package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Position {

    int positionX;
    int positionY;

    public void update(int positionX, int positionY){
        this.positionX = positionX / 30;
        this.positionY = positionY / 30;

//        System.out.println("x : " + positionX + "y : "+ positionY);

    }


    public void draw(Graphics2D g2){

        // draw image
        g2.drawString("x : " + positionX + "y :" + positionY, 30, 30);

    }


}
