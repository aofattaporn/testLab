package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {

    // INJECTION
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        getTileImages();


    }

    // method get image
    public void getTileImages(){
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/board.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){

            g2.drawImage(tile[0].image, x, y, gp.titleSize, gp.titleSize, null);
            col++;
            x += gp.titleSize;

            if (col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.titleSize;
            }

        }

    }
}
