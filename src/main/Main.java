package main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
//        WriteToFile.writeFile();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("GAME ROBOT");

        // RUNNING GAME
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(gamePanel.state);

        gamePanel.startGameThread();

    }
}
