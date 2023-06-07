package main.ui;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    private int playerX;
    private int playerY;

    public PlayerPanel() {
        playerX = 0;
        playerY = 0;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
    }

    public void setPlayerPosition(int x, int y) {
        playerX = x;
        playerY = y;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(playerX, playerY, 10, 10);
    }
}
