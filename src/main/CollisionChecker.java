package main;

import entity.Entity;

import java.awt.*;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.soidArea.x;
        int entityRightWorldX = entity.worldX + entity.soidArea.x + entity.soidArea.width;
        int entityTopWorldY = entity.worldY + entity.soidArea.y;
        int entityBottomWorldY = entity.worldY + entity.soidArea.y + entity.soidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.titleSize;
        int entityRightCol = entityRightWorldX / gp.titleSize;
        int entityTopRow = entityTopWorldY / gp.titleSize;
        int entityBottomRow = entityBottomWorldY / gp.titleSize;


    }
}
