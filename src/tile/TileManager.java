package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    // INJECTION
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    // method provide tile
    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImages();
        loadMap("../res/maps/minimap.txt");

    }

    // method get image
    public void getTileImages() {
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/board.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/board2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method draw board
    public void draw(Graphics2D g2) {

        // draw mini map
        // miniMap(g2);
        miniMapByFile(g2);


    }

    // draw miniMap
    public void miniMap(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            g2.drawImage(tile[0].image, x, y, gp.titleSize, gp.titleSize, null);
            col++;
            x += gp.titleSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.titleSize;
            }
        }

    }

    // draw miniMap
    public void miniMapByFile(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.titleSize, gp.titleSize, null);
            col++;
            x += gp.titleSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.titleSize;
            }
        }

    }

    // method load map
    public void loadMap(String file) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = bufferedReader.readLine();

                while (col < gp.maxScreenCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                // read new line
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
        }
    }

}
