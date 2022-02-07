package main;

import tile.Tile;
import tile.TileManager;

import java.awt.*;

public class Bomb {

    TileManager tileManager;
    GamePanel gp;

    public Bomb(TileManager tileManager, GamePanel gp) {
        this.tileManager = tileManager;
        this.gp = gp;
    }


    public void draw(Graphics2D g2, int randCol[], int[] randRow){

        for (int i = 0; i < randCol.length; i++){
            for (int j =0; j < randRow.length; j++){
                // draw image
                g2.drawImage(tileManager.tile[1].image, randCol[i], randRow[j], gp.titleSize, gp.titleSize, null);
                break;
            }
        }
    }
}
